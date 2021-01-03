package entity;

public interface UniVariableRealFunctionExtremaFinder {
    double findExtrema(UniVariableRealFunction function, double startOfSegment, double endOfSegment, double epsilon);

}
