package find_zeros_method;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionZeroFinder;
import org.apache.commons.math3.util.FastMath;

public class SecantMethod extends IterationsCounter
        implements UniVariableRealFunctionZeroFinder {

    @Override
    public double findZero(UniVariableRealFunction function,
                           double startOfSegment,
                           double endOfSegment,
                           double epsilon
    ) {
        int amountOfIterations = 0;
        while(FastMath.abs(endOfSegment - startOfSegment) > epsilon) {
            startOfSegment =
                    endOfSegment -
                    (endOfSegment - startOfSegment) * function.value(endOfSegment) /
                    (function.value(endOfSegment) - function.value(startOfSegment));

            endOfSegment =
                    startOfSegment -
                    (startOfSegment - endOfSegment) * function.value(startOfSegment) /
                    (function.value(startOfSegment) - function.value(endOfSegment));
            amountOfIterations += 1;
        }
        this.amountOfIterationsLastCalculate = amountOfIterations;
        return endOfSegment;
    }
}
