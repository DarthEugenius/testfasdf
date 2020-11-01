package methods;

import entity.UniVariableRealFunction;

public class MiddleRectanglesIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        double alpha = Math.abs(integrateTo - integrateFrom) / iterations;

        double integralValue = 0.000000000000000000000000000000000000000000000;

        for (int i = 0; i < iterations; i++) {
            integralValue = integralValue + function.value((i + 0.5) * alpha) * alpha;
        }
        return integralValue;
    }
}