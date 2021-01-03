package find_zeros_method;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionZeroFinder;

public class NewtonMethod extends IterationsCounter
        implements UniVariableRealFunctionZeroFinder {
    final static double INCREMENT_OF_ARGUMENT = 1e-15;

    double derivativeAtPoint(UniVariableRealFunction function, double x) {
        double incrementOfFunction =
                function.value(x + INCREMENT_OF_ARGUMENT) - function.value(x);
        return incrementOfFunction / INCREMENT_OF_ARGUMENT;
    }

    @Override
    public double findZero(UniVariableRealFunction function,
                           double startOfSegment,
                           double endOfSegment,
                           double epsilon
    ) {
        int amountOfIterations = 1;
        UniVariableRealFunction derivative = x -> derivativeAtPoint(function, x);
        double zeroOfFunction = startOfSegment -
                function.value(startOfSegment) / derivative.value(startOfSegment);
        while (Math.abs(zeroOfFunction - startOfSegment) > epsilon) {
            startOfSegment = zeroOfFunction;
            zeroOfFunction =
                    startOfSegment -
                            function.value(startOfSegment) /
                                    derivative.value(startOfSegment);
            amountOfIterations += 1;
        }
        this.amountOfIterationsLastCalculate = amountOfIterations;
        return zeroOfFunction;
    }
}
