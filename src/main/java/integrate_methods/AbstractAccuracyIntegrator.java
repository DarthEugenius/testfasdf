package integrate_methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionIntegrator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractAccuracyIntegrator implements UniVariableRealFunctionIntegrator {

    @Override
    public double integrate(double accuracy,
                            UniVariableRealFunction function,
                            double integrateFrom,
                            double integrateTo) {
        return integrate(1, accuracy, function, integrateFrom, integrateTo);
    }

    @Override
    public double integrate(int iterations, double accuracy,
                            UniVariableRealFunction function,
                            double integrateFrom,
                            double integrateTo) {
        int counter = iterations;
        double previousIntegralValue = integrate(counter, function, integrateFrom, integrateTo);
        double currentIntegralValue = integrate(2 * counter, function, integrateFrom, integrateTo);
        while (FastMath.abs(
                        currentIntegralValue
                        -
                        previousIntegralValue)
                > accuracy
        ) {

            previousIntegralValue = currentIntegralValue;
            counter *= 2;
            currentIntegralValue = integrate(2 * counter, function, integrateFrom, integrateTo);
        }
        return previousIntegralValue;
    }
}
