package integrate_methods;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;

public class TrapezoidalIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        final double delta = FastMath.abs(integrateFrom - integrateTo) / iterations;

        double integralValue = 0.000000000000000000000000000000000000000000000000;

        for (int i = 0; i < iterations; i++) {

            double currentX = integrateFrom + i * delta;
            double nextX = integrateFrom + (i + 1) * delta;

            integralValue += (function.value(currentX) + function.value(nextX)) / 2 * delta;
        }
        return integralValue;
    }
}