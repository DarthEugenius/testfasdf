package methods;

import entity.UniVariableRealFunction;
import entity.UniVariableRealFunctionIntegrator;

public abstract class AbstractAccuracyIntegrator implements UniVariableRealFunctionIntegrator {

    @Override
    public double integrate(double accuracy,
                            UniVariableRealFunction function,
                            double integrateFrom,
                            double integrateTo) {
        return integrate(1, accuracy, function, integrateFrom, integrateTo);
    }

    @Override
    public double integrate(int iterations,double accuracy,
                            UniVariableRealFunction function,
                            double integrateFrom,
                            double integrateTo) {
        int counter = iterations;
        double previousIntegralValue = integrate(counter, function, integrateFrom, integrateTo);
        while (Math.abs(
                integrate(2 * counter, function, integrateFrom, integrateTo)
                        -
                        previousIntegralValue)
                > accuracy
        ) {
            previousIntegralValue = integrate(2 * counter, function, integrateFrom, integrateTo);
            counter *= 2;
        }
        return integrate(counter, function, integrateFrom, integrateTo);
    }
}
