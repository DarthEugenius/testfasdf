package integrate_methods;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;

public class MiddleRectanglesIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations,
                            UniVariableRealFunction function,
                            double integrateFrom,
                            double integrateTo) {
        double alpha = FastMath.abs(integrateTo - integrateFrom) / iterations;

        double integralValue = 0.0;
        double currentX = integrateFrom + alpha / 2;

        for (int i = 0; i < iterations; i++) {
            integralValue = integralValue + function.value(currentX) * alpha;
            currentX += alpha;
        }
        return integralValue;
    }
}