package find_extrema_methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionExtremaFinder;

public class SegmentDichotomyMethod implements UniVariableRealFunctionExtremaFinder {
    @Override
    public double findExtrema(
            UniVariableRealFunction function,
            double startOfSegment,
            double endOfSegment,
            double epsilon) {
        double y1;
        double y2;
        double center = (startOfSegment + endOfSegment) / 2;
        double error = endOfSegment - startOfSegment;
        while (error > epsilon) {
            y1 = function.value(center - epsilon);
            y2 = function.value(center + epsilon);
            if (y1 < y2) {
                endOfSegment = center;
            } else {
                startOfSegment = center;
            }
            center = (startOfSegment + endOfSegment) / 2;
            System.out.println("X[n] = " + center + ";\t X[n]-X[n-1] = " + error);
            error = endOfSegment - startOfSegment;
        }
        return center;
    }
}
