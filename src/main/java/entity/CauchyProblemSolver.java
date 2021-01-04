package entity;

public interface CauchyProblemSolver {
    double[] solve(TwoVariablesRealFunction derivative, double startOfSegment, double valueOfFunctionInStartOfSegment, double endOfSegment, double step);
    int getOrder();
}
