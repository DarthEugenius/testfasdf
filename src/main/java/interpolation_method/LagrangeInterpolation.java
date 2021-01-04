package interpolation_method;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import util.Utils;

public class LagrangeInterpolation extends AbstractInterpolator {
    @Override
    public double[] interpolate(UniVariableRealFunction function,
                                double startOfSegment,
                                double endOfSegment,
                                int amountOfNodes) {
        this.amountOfNodes = amountOfNodes;
        double[] xOriginal =
                Utils.getArrayOfArguments(
                        startOfSegment,
                        endOfSegment,
                        DEFAULT_AMOUNTS_OF_SEGMENTS);
        this.valuesOfArgument = xOriginal.clone();
        double[] x = Utils.getArrayOfArguments(startOfSegment,
                endOfSegment,
                amountOfNodes);
        this.nodes = x;
        double[] y =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        function,
                        x);
        this.valuesOfFunctionInNodes = y;
        return interpolate(y, x);
    }

    @Override
    public double[] interpolate(double[] valuesOfFunction,
                                double[] valuesOfArgument) {
        this.resultFunction = new PolynomialLagrangeForm(
                valuesOfArgument,
                valuesOfFunction);
        this.valuesOfInterpolatedFunction =
                Utils.getArrayOfValuesOfUniVariableRealFunction(
                        resultFunction,
                        this.valuesOfArgument);
        return this.valuesOfInterpolatedFunction;
    }
}

class PolynomialLagrangeForm implements UniVariableRealFunction {


    private final double[] x;
    private final double[] y;


    public PolynomialLagrangeForm(double[] x, double[] y) {
        this.x = new double[x.length];
        this.y = new double[y.length];
        System.arraycopy(x, 0, this.x, 0, x.length);
        System.arraycopy(y, 0, this.y, 0, y.length);

        if (!verifyInterpolationArray(x, false)) {
            MathArrays.sortInPlace(this.x, this.y);
            verifyInterpolationArray(this.x, true);
        }
    }


    public double value(double z) {
        return evaluateInternal(x, y, z);
    }


    private static double evaluateInternal(
            double[] x,
            double[] y,
            double z) {
        int nearest = 0;
        final int n = x.length;
        final double[] c = new double[n];
        final double[] d = new double[n];
        double min_dist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            c[i] = y[i];
            d[i] = y[i];
            final double dist = FastMath.abs(z - x[i]);
            if (dist < min_dist) {
                nearest = i;
                min_dist = dist;
            }
        }

        double value = y[nearest];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                final double tc = x[j] - z;
                final double td = x[i + j] - z;
                final double divider = x[j] - x[i + j];
                final double w = (c[j + 1] - d[j]) / divider;
                c[j] = tc * w;
                d[j] = td * w;
            }
            if (nearest < 0.5 * (n - i + 1)) {
                value += c[nearest];
            } else {
                nearest--;
                value += d[nearest];
            }
        }

        return value;
    }

    public static boolean verifyInterpolationArray(
            double[] x,
            boolean abort) {
        return MathArrays.checkOrder(
                x,
                MathArrays.OrderDirection.INCREASING,
                true,
                abort);
    }
}
