package differential_equations;

import entity.CauchyProblemSolver;
import entity.TwoVariablesRealFunction;

public class RungeKuttaMethodSixthOrder implements CauchyProblemSolver {
    public static final double d = Math.sqrt(21);

    @Override
    public String toString() {
        return "Метод Рунге-Кутты 6-ого порядка";
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
        double k1, k2, k3, k4, k5, k6, k7;
        double[] y = new double[amountOfPoints + 1];
        y[0] = valueOfFunctionInStartOfSegment;
        for (int i = 1; i <= amountOfPoints; i++) {
            k1 = derivative.value(
                    xCurrent,
                    y[i-1]) * step;
            k2 = derivative.value(
                    (xCurrent + step),
                    (y[i - 1] + k1)) * step;
            k3 = derivative.value(
                    (xCurrent + step / 2),
                    (y[i - 1] + (3 * k1 + k2) / 8)) * step;
            k4 = derivative.value((xCurrent + 2 * step / 3),
                    (y[i - 1] + (8 * k1 + 2 * k2 +
                            8 * k3) / 27)) * step;
            k5 = derivative.value((xCurrent + (7 - d) * step / 14),
                    (y[i - 1] + (3 * (3 * d - 7) * k1 - 8 * (7 - d) * k2 +
                              48 * (7 - d) * k3 - 3 * (21 - d) * k4) / 392)) * step;
            k6 = derivative.value((xCurrent + (7 + d) * step / 14),
                    (y[i - 1] + (-5 * (231 + 51 * d) * k1 - 40 * (7 + d) * k2 -
                            320 * d * k3 + 3 * (21 + 121 * d) * k4 +
                            392 * (6 + d) * k5) / 1960)) * step;
            k7 = derivative.value((xCurrent + step),
                    (y[i - 1] + (15 * (22 + 7 * d) * k1 + 120 * k2 +
                            40 * (7 * d - 5) * k3 - 63 * (3 * d - 2) * k4 -
                            14 * (49 + 9 * d) * k5 + 70 * (7 - d) * k6) / 180))
                    * step;
            y[i] = y[i-1] + (9 * k1 + 64 * k3 + 49 * k5 + 49 * k6 + 9 * k7) / 180;
            xCurrent += step;
        }

        return y;
    }

    @Override
    public int getOrder() {
        return 6;
    }
}

