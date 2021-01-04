package differential_equations;

import entity.CauchyProblemSolver;
import entity.TwoVariablesRealFunction;

public class EulerMethod implements CauchyProblemSolver {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public String toString() {
        return "Метод Эйлера";
    }
    @Override
    public double[] solve(
            TwoVariablesRealFunction derivative,
            double startOfSegment,
            double valueOfFunctionInStartOfSegment,
            double endOfSegment,
            double step) {

        int amountOfPoints = (int) ((endOfSegment - startOfSegment) / step);
        double xCurrent = startOfSegment;

        double[] y = new double[amountOfPoints + 1];
        y[0] = valueOfFunctionInStartOfSegment;
        for (int i = 1; i <= amountOfPoints; i++) {
            y[i] = y[i - 1] + step * derivative.value(xCurrent, y[i - 1]);
            xCurrent += step;
        }

        return y;
    }
}
