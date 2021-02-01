package entity;

/**
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 */

public interface CauchyProblemFirstOrderSolver {
    double[] solve(TwoVariablesRealFunction derivative,
                   double startOfSegment,
                   double valueOfFunctionInStartOfSegment,
                   double endOfSegment,
                   double step);
    int getOrder();
}
