package interpolation_method;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import util.Utils;

public class SplineInterpolationMethod extends AbstractInterpolator {

    @Override
    public double[] interpolate(UniVariableRealFunction function,
                                double startOfSegment,
                                double endOfSegment,
                                int amountOfNodes) {
        this.amountOfNodes = amountOfNodes;

        double[] xOriginal = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfArgument = xOriginal.clone();
        this.nodes = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                amountOfNodes);
        this.valuesOfFunctionInNodes =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        function,
                        this.nodes);

        return interpolate(this.valuesOfFunctionInNodes, this.nodes);
    }

    @Override
    public double[] interpolate(
            double[] valuesOfFunction,
            double[] valuesOfArgument) {
        /*SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction splineFunction =
                interpolator.interpolate(
                        valuesOfArgument,
                valuesOfFunction);
        this.resultFunction = splineFunction::value;*/
        int size = valuesOfArgument.length;
        SplineOnSegment[] mSplines = new SplineOnSegment[size];
        for (int i = 0; i < size; i++) {
            mSplines[i] = new SplineOnSegment();
            mSplines[i].x = valuesOfArgument[i];
            mSplines[i].a = valuesOfFunction[i];
        }
        mSplines[0].c = mSplines[size - 1].c = 0;
        double[] alpha = new double[size - 1];
        double[] beta = new double[size - 1];
        alpha[0] = beta[0] = 0.0;
        for (int i = 1; i < size - 1; i++) {
            double hi = valuesOfArgument[i] - valuesOfArgument[i - 1];
            double hi1 = valuesOfArgument[i + 1] - valuesOfArgument[i];
            double z = (hi * alpha[i - 1] + 2.0 * (hi + hi1));
            alpha[i] = -hi1 / z;
            beta[i] = (6.0 * ((valuesOfFunction[i + 1]
                    - valuesOfFunction[i]) /
                    hi1 - (valuesOfFunction[i] - valuesOfFunction[i - 1]) / hi) - hi * beta[i - 1]) / z;
        }

        for (int i = size - 2; i > 0; i--) {
            mSplines[i].c = alpha[i] * mSplines[i + 1].c + beta[i];
        }

        for (int i = size - 1; i > 0; --i) {
            double hi = valuesOfArgument[i] - valuesOfArgument[i - 1];
            mSplines[i].d = (mSplines[i].c - mSplines[i - 1].c) / hi;
            mSplines[i].b = hi *
                    (2.0 * mSplines[i].c + mSplines[i - 1].c)
                    / 6.0 + (valuesOfFunction[i] - valuesOfFunction[i - 1])
                    / hi;
        }
        this.resultFunction = x -> {

            SplineOnSegment s;

            if (x <= mSplines[0].x) {
                s = mSplines[0];
            } else if (x >= mSplines[size - 1].x) {
                s = mSplines[size - 1];
            } else {
                int i = 0;
                int j = size - 1;
                while (i + 1 < j) {
                    int k = i + (j - i) / 2;
                    if (x <= mSplines[k].x) {
                        j = k;
                    } else {
                        i = k;
                    }
                }
                s = mSplines[j];
            }

            double dx = x - s.x;
            return s.a + (s.b + (s.c / 2.0 + s.d * dx / 6.0) * dx) * dx;
        };
        this.valuesOfInterpolatedFunction =
                Utils.getArrayOfValuesOfUniVariableRealFunction(resultFunction,
                        this.valuesOfArgument);
        return valuesOfInterpolatedFunction;
    }

    static class SplineOnSegment {
        double a,
                b,
                c,
                d,
                x;

    }
}
