package entity;

/**
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 */

public interface UniVariableRealFunctionApproximator {
    double[] approximate(UniVariableRealFunction function, double startOfSegment, double endOfSegment, int amountOfNodes);
    double[] approximate(double[] valuesOfFunction, double[] valuesOfArgument);
}
