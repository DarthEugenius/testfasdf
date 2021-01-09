package find_extrema_methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionExtremaFinder;
import org.apache.commons.math3.util.FastMath;

public class TangentsMethod implements UniVariableRealFunctionExtremaFinder {
    final static double INCREMENT_OF_ARGUMENT = 1e-8;

    double derivativeAtPoint(UniVariableRealFunction function, double x) {
        double incrementOfFunction =
                function.value(x + INCREMENT_OF_ARGUMENT) - function.value(x);
        return incrementOfFunction / INCREMENT_OF_ARGUMENT;
    }

    @Override
    public double findExtrema(UniVariableRealFunction function,
                              double startOfSegment,
                              double endOfSegment,
                              double epsilon) {
        UniVariableRealFunction derivative = x -> derivativeAtPoint(function, x);
        UniVariableRealFunction secondDerivative = x ->
                derivativeAtPoint(derivative, x);
        double xPrev = (startOfSegment + endOfSegment) / 2;
        double xCurrent =
                xPrev -
                        derivative.value(xPrev) /
                                secondDerivative.value(xPrev);
        double error = xCurrent - xPrev;
        while (FastMath.abs(error) > epsilon) {
            xPrev = xCurrent;
            xCurrent =
                    xPrev -
                            derivative.value(xPrev) /
                                    secondDerivative.value(xPrev);
            System.out.println("X[n] = " + xCurrent + ";\t X[n]-X[n-1] = " + error);
            error = xCurrent - xPrev;
        }
        return xCurrent;
    }
}
