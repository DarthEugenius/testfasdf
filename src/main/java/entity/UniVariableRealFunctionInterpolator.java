package entity;

public interface UniVariableRealFunctionInterpolator {
    double[] interpolate(UniVariableRealFunction function, double startOfSegment, double endOfSegment, int amountOfNodes);
    double[] interpolate(double[] valuesOfFunction, double[] valuesOfArgument);
}
