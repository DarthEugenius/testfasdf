package entity;

public interface UniVariableRealFunctionZeroFinder {
    double findZero(UniVariableRealFunction function, double startOfSegment, double endOfSegment, double epsilon);
}
