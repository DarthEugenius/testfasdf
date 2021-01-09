package find_extrema_methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionExtremaFinder;
import org.apache.commons.math3.util.FastMath;

public class SecantMethod
        implements UniVariableRealFunctionExtremaFinder {
    final static double INCREMENT_OF_ARGUMENT = 1e-8;

    double derivativeAtPoint(UniVariableRealFunction function, double x) {

        double incrementOfFunction =
                function.value(x + INCREMENT_OF_ARGUMENT) - function.value(x);
        return incrementOfFunction / INCREMENT_OF_ARGUMENT;
    }

    @Override
    public double findExtrema(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            double epsilon) {
        UniVariableRealFunction derivative = x -> derivativeAtPoint(function, x);
        double extrema;
        double error = FastMath.abs(startOfSegment - endOfSegment);
        while (FastMath.abs(startOfSegment - endOfSegment) > epsilon) {
            extrema = endOfSegment
                    - derivative.value(endOfSegment)
                    / (derivative.value(endOfSegment)
                    - derivative.value(startOfSegment)
                    / (endOfSegment - startOfSegment));
            startOfSegment = endOfSegment;
            endOfSegment = extrema;
            System.out.println("X[n] = " + startOfSegment +
                    ";\t X[n]-X[n-1] = " + error);
            error = FastMath.abs(startOfSegment - endOfSegment);
        }
        return startOfSegment;

    }
}
