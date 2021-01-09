package integrate_methods;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;

public class LeftRectanglesIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        double delta = FastMath.abs(integrateFrom - integrateTo) / iterations;
        double integralValue = 0.0;
        double currentX = integrateFrom;
        for (int i = 0; i < iterations; i++) {
            integralValue += function.value(currentX) * delta;
            currentX += delta;
        }
        return integralValue;
    }
}