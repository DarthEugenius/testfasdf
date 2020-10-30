package benchmark.executors;

import benchmark.handlers.IntegrateMethodBenchmarkConfigurator;
import benchmark.handlers.IntegrationBenchmarkConfigurator;
import infrastructure.BenchmarkConfigurator;
import infrastructure.ClassesCollectorForBenchmark;
import infrastructure.UniVariableRealFunctionIntegrator;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * This class executes benchmark on each {@code integrate} method of class, which implements
 * {@code UniVariableRealFunctionIntegrator}.
 *
 * @version 1.0
 * @see BenchmarkConfigurator - a superInterface for each benchmark configurator.
 * @see IntegrationBenchmarkConfigurator - a benchmark configurator for integrtion.
 * @see IntegrateMethodBenchmarkConfigurator
 */
public class ClassesCollectorForEachIntegrateMethodBenchmark implements
        ClassesCollectorForBenchmark<UniVariableRealFunctionIntegrator> {

    private final IntegrationBenchmarkConfigurator configurator;

    public ClassesCollectorForEachIntegrateMethodBenchmark(IntegrationBenchmarkConfigurator configurator) {
        this.configurator = configurator;
    }

    @Override
    public void executeBenchmarkForAllImplementations(Class<UniVariableRealFunctionIntegrator> superInterface) {

        /* Get all implementations of UniVariableRealFunctionIntegrator interface */
        Set<Class<? extends UniVariableRealFunctionIntegrator>> subTypesOfIntegrator =
                new Reflections("").getSubTypesOf(superInterface);

        /* Execute benchmark of each implementation */
        for (Class<? extends UniVariableRealFunctionIntegrator> integratorClazz : subTypesOfIntegrator) {

            /* Check if a given class is not interface of an abstract class */
            if (!integratorClazz.isInterface() && !Modifier.isAbstract(integratorClazz.getModifiers())) {
                try {
                    configurator.setIntegrator(integratorClazz.newInstance());
                    configurator.configureBenchmark();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}