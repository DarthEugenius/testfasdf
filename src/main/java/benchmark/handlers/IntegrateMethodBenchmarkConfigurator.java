package benchmark.handlers;

import infrastructure.UniVariableRealFunctionIntegrator;
import infrastructure.UniVariableRealFunction;

public class IntegrateMethodBenchmarkConfigurator implements IntegrationBenchmarkConfigurator {

    private UniVariableRealFunctionIntegrator integrator;

    private UniVariableRealFunction function;

    private double integrateFrom;

    private double integrateTo;

    private double[] accuracies;

    private int[] iterations;

    public IntegrateMethodBenchmarkConfigurator(UniVariableRealFunction function,
                                                double integrateFrom,
                                                double integrateTo,
                                                double[] accuracies) {
        this.setFunction(function)
                .setIntegrateFrom(integrateFrom)
                .setIntegrateTo(integrateTo)
                .setAccuracies(accuracies);
    }

    public IntegrateMethodBenchmarkConfigurator(UniVariableRealFunction function,
                                                double integrateFrom,
                                                double integrateTo,
                                                int[] iterations) {
        this.setFunction(function)
                .setIntegrateFrom(integrateFrom)
                .setIntegrateTo(integrateTo)
                .setIterations(iterations);
    }

    @Override
    public IntegrationBenchmarkConfigurator setIntegrator(UniVariableRealFunctionIntegrator integrator) {
        this.integrator = integrator;
        return this;
    }

    @Override
    public IntegrationBenchmarkConfigurator setFunction(UniVariableRealFunction function) {
        this.function = function;
        return this;
    }

    @Override
    public IntegrationBenchmarkConfigurator setIntegrateFrom(double integrateFrom) {
        this.integrateFrom = integrateFrom;
        return this;
    }

    @Override
    public IntegrationBenchmarkConfigurator setIntegrateTo(double integrateTo) {
        this.integrateTo = integrateTo;
        return this;
    }

    @Override
    public IntegrationBenchmarkConfigurator setAccuracies(double[] accuracies) {
        this.accuracies = accuracies;
        return this;
    }

    @Override
    public IntegrationBenchmarkConfigurator setIterations(int[] iterations) {
        this.iterations = iterations;
        return this;
    }

    @Override
    public void benchmarkWithAccuracies() {
        System.out.println("IMPLEMENTATION " + integrator.getClass());
        for (double accuracyItem : accuracies) {
            long before = System.nanoTime();
            double integralValue = integrator.integrate(accuracyItem, function, integrateFrom, integrateTo);
            long after = System.nanoTime();
            System.out.println("Integral value: " + integralValue +
                    " for accuracy: " + accuracyItem +
                    ", it took time: " + (after - before));
        }
    }

    @Override
    public void benchmarkWithIterations() {
        System.out.println("IMPLEMENTATION " + integrator.getClass());
        for (double iterationItem : iterations) {
            long before = System.nanoTime();
            double integralValue = integrator.integrate(iterationItem, function, integrateFrom, integrateTo);
            long after = System.nanoTime();
            System.out.println("Integral value: " + integralValue +
                    " for " + iterationItem + " iterations" +
                    ", it took time: " + (after - before));
        }
    }

    @Override
    public void configureBenchmark() {
        if (accuracies == null) {
            benchmarkWithIterations();
        } else if (iterations == null) {
            benchmarkWithAccuracies();
        } else {
            System.out.println("\n" + "NOW COMES TWO BENCHMARKS:" + "\n");

            System.out.println("THE ACCURACIES BENCHMARK:");
            benchmarkWithAccuracies();

            System.out.println();

            System.out.println("...AND NOW THE ITERATIONS BENCHMARK:");
            benchmarkWithIterations();
        }
    }
}
