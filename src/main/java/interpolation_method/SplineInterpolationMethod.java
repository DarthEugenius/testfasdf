package interpolation_method;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import util.Utils;

public class SplineInterpolationMethod extends AbstractInterpolator {

    @Override
    public double[] interpolate(UniVariableRealFunction function, double startOfSegment, double endOfSegment, int amountOfNodes) {

        double[] xOriginal = Utils.getArrayOfArguments(startOfSegment, endOfSegment, DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfArgument = xOriginal.clone();
        double[] x = Utils.getArrayOfArguments(startOfSegment, endOfSegment, amountOfNodes);
        double[] y = Utils.getArrayOfValuesOfUniVariableRealFunction(function, x);
        return interpolate(y, x);
    }

    @Override
    public double[] interpolate(double[] valuesOfFunction, double[] valuesOfArgument) {
        SplineInterpolator interpolator = new SplineInterpolator();
        PolynomialSplineFunction splineFunction = interpolator.interpolate(valuesOfArgument, valuesOfFunction);
        this.valuesOfInterpolatedFunction = Utils.getArrayOfValuesOfUniVariableRealFunction(splineFunction::value, this.valuesOfArgument);
        return valuesOfInterpolatedFunction;
    }
}
