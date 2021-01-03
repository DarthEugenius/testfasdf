package integrate_methods;

import entity.UniVariableRealFunction;

public class RightRectanglesIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        double delta = Math.abs(integrateFrom - integrateTo) / iterations;
        double integralValue = 0.0;
        double currentX = integrateFrom + delta;
        for (int i = 0; i < iterations; i++) {
            integralValue += function.value(currentX) * delta;
            currentX += delta;
        }
        return integralValue;
    }
}

