package find_extrema_methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionExtremaFinder;

public class GoldenRatioMethod implements UniVariableRealFunctionExtremaFinder {

    public static double PHI = (1 + Math.sqrt(5)) / 2;

    @Override
    public double findExtrema(UniVariableRealFunction function, double startOfSegment, double endOfSegment, double epsilon) {

        double result = (startOfSegment + endOfSegment) / 2;
        while (Math.abs(startOfSegment - endOfSegment) > epsilon) {
            double x_1 = endOfSegment - (endOfSegment - startOfSegment) / PHI;
            double x_2 = startOfSegment + (endOfSegment - startOfSegment) / PHI;
            if (function.value(x_1) > function.value(x_2)) {
                startOfSegment = x_1;
            } else {
                endOfSegment = x_2;
            }
            result = (startOfSegment + endOfSegment) / 2;
        }
        return result;
    }
}
