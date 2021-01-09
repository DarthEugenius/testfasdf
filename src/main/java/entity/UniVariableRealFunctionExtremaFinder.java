package entity;

/**
 * @author Evgeniy Ternovoy
 */

public interface UniVariableRealFunctionExtremaFinder {
    double findExtrema(UniVariableRealFunction function, double startOfSegment, double endOfSegment, double epsilon);

}
