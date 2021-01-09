package integrate_methods;

import entity.UniVariableRealFunction;
import org.apache.commons.math3.util.FastMath;

public class SimpsonIntegrator extends AbstractAccuracyIntegrator {

    @Override
    public double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        double oddSum = 0.0;
        double evenSum = 0.0;
        double lim = iterations / 2;
        double alpha = FastMath.abs(integrateTo - integrateFrom) / iterations;
        double integralValue = (function.value(integrateFrom) + function.value(integrateTo));

        for (int i = 1; i <= lim; i++)
            oddSum += function.value(integrateFrom + (2 * i - 1) * alpha);
        oddSum *= 4;

        for (int i = 1; i < lim; i++)
            evenSum += function.value(integrateFrom + (2 * i) * alpha);
        evenSum *= 2;
        integralValue += oddSum + evenSum;
        integralValue *= (alpha / 3);
        return integralValue;
    }
}