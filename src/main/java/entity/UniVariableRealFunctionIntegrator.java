package entity;

/**
 * A root interface for each integrator.
 *
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 * @version 1.0
 */
public interface UniVariableRealFunctionIntegrator {

    double integrate(int iterations, UniVariableRealFunction function, double integrateFrom, double integrateTo);

    double integrate(double accuracy, UniVariableRealFunction function, double integrateFrom, double integrateTo);

    double integrate(int iterations, double accuracy, UniVariableRealFunction function, double integrateFrom, double integrateTo);
}