package find_zeros_method;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionZeroFinder;

public class DichotomyMethod extends IterationsCounter
        implements UniVariableRealFunctionZeroFinder {

    @Override
    public double findZero(UniVariableRealFunction function,
                           double startOfSegment,
                           double endOfSegment,
                           double epsilon
    ) {
        int amountOfIterations = 1;
        double zeroOfFunction = (startOfSegment + endOfSegment) / 2;
        while (endOfSegment - startOfSegment > 2 * epsilon) {
            if (function.value(zeroOfFunction) * function.value(endOfSegment) >= 0) {
                endOfSegment = zeroOfFunction;
            } else
                startOfSegment = zeroOfFunction;
            zeroOfFunction = (startOfSegment + endOfSegment) / 2;
            amountOfIterations += 1;
        }
        this.amountOfIterationsLastCalculate = amountOfIterations;
        return zeroOfFunction;
    }


}
