package infrastructure;

/**
 * A root interface to each integrator.
 */
public interface UniVariableRealFunctionIntegrator {

    double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo);

    double integrate(double accuracy, UniVariableRealFunction function, double integrateFrom, double integrateTo);
}