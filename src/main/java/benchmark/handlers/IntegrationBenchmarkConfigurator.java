package benchmark.handlers;

import infrastructure.BenchmarkConfigurator;
import infrastructure.UniVariableRealFunction;
import infrastructure.UniVariableRealFunctionIntegrator;

/**
 * An extension for {@code BenchmarkConfigurator} interface to make benchmark for integration methods.
 *
 * @version 1.0
 */
public interface IntegrationBenchmarkConfigurator extends BenchmarkConfigurator {

    IntegrationBenchmarkConfigurator setIntegrator(UniVariableRealFunctionIntegrator integrator);

    IntegrationBenchmarkConfigurator setFunction(UniVariableRealFunction uniVariableRealFunction);

    IntegrationBenchmarkConfigurator setIntegrateFrom(double integrateFrom);

    IntegrationBenchmarkConfigurator setIntegrateTo(double integrateTo);

    IntegrationBenchmarkConfigurator setAccuracies(double[] accuracies);

    IntegrationBenchmarkConfigurator setIterations(int[] iterations);

    void benchmarkWithAccuracies();

    void benchmarkWithIterations();
}
