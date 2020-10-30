package methods;

import infrastructure.UniVariableRealFunction;

public class LeftRectanglesIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        double delta = Math.abs(integrateFrom - integrateTo) / iterations;

        double integralValue = 0.000000000000000000000000000000000000000000000;

        for (int i = 0; i < iterations; i++) {

            double currentX = integrateFrom + i * delta;

            integralValue += function.value(currentX) * delta;

        }
        return integralValue;
    }
}