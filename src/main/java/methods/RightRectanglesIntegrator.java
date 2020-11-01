package methods;

import entity.UniVariableRealFunction;

public class RightRectanglesIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        double delta = Math.abs(integrateFrom - integrateTo) / iterations;

        double integralValue = 0.000000000000000000000000000000000000000000000;

        for (int i = 1; i < iterations; i++) {

            double currentX = integrateFrom + i * delta;

            integralValue += function.value(currentX) * delta;

        }
        return integralValue;
    }
}

