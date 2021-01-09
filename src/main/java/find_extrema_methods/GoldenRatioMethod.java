package find_extrema_methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionExtremaFinder;
import org.apache.commons.math3.util.FastMath;

public class GoldenRatioMethod implements UniVariableRealFunctionExtremaFinder {

    public static double PHI = (1 + FastMath.sqrt(5)) / 2;

    @Override
    public double findExtrema(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            double epsilon) {

        double result = (startOfSegment + endOfSegment) / 2;
        double error = FastMath.abs(startOfSegment - endOfSegment);
        while (error > epsilon) {
            double x1 = endOfSegment - (endOfSegment - startOfSegment) / PHI;
            double x2 = startOfSegment + (endOfSegment - startOfSegment) / PHI;
            if (function.value(x1) > function.value(x2)) {
                startOfSegment = x1;
            } else {
                endOfSegment = x2;
            }
            result = (startOfSegment + endOfSegment) / 2;
            System.out.println("X[n] = " + result + ";\t X[n]-X[n-1] = " + error);
            error = FastMath.abs(startOfSegment - endOfSegment);
        }
        return result;
    }
}
