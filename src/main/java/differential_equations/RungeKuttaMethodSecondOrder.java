package differential_equations;

import entity.CauchyProblemFirstOrderSolver;
import entity.TwoVariablesRealFunction;

public class RungeKuttaMethodSecondOrder implements CauchyProblemFirstOrderSolver {

    @Override
    public String toString() {
        return "Метод Рунге-Кутты 2-ого порядка";
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
        double k1, k2;
        double[] y = new double[amountOfPoints + 1];
        y[0] = valueOfFunctionInStartOfSegment;
        for (int i = 1; i <= amountOfPoints; i++) {
            k1 = step * derivative.value(
                    xCurrent,
                    y[i - 1]);
            k2 = step * derivative.value(
                    xCurrent + step / 2,
                    y[i - 1] + k1 / 2);

            y[i] = y[i - 1] + (k1 + k2 * 2) / 6;
            xCurrent += step;
        }
        return y;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
