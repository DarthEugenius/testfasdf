package entity;

/**
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 */

public interface UniVariableRealFunctionZeroFinder {
    double findZero(UniVariableRealFunction function, double startOfSegment, double endOfSegment, double epsilon);
}
