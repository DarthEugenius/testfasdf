package methods;

import infrastructure.UniVariableRealFunction;
import infrastructure.UniVariableRealFunctionIntegrator;

public abstract class AbstractAccuracyIntegrator implements UniVariableRealFunctionIntegrator {

    @Override
    public double integrate(double accuracy, UniVariableRealFunction function, double integrateFrom, double integrateTo) {
        int counter = 1;
        while(
                Math.abs(integrate(counter, function, integrateFrom, integrateTo)
                                -
                                integrate(counter + 1, function, integrateFrom, integrateTo))
                        >
                        accuracy
        ) {
            counter++;
        }
        return integrate(counter + 1, function, integrateFrom, integrateTo);
    }
}
