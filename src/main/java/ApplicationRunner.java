import benchmark.executors.ClassesCollectorForEachIntegrateMethodBenchmark;
import benchmark.handlers.IntegrateMethodBenchmarkConfigurator;
import infrastructure.ClassesCollectorForBenchmark;
import infrastructure.UniVariableRealFunctionIntegrator;

public class ApplicationRunner {

    /* Номер варианта */
    private static final int p = 7;

    private static int q =10;

    int x = 10;
    private static double[] ACCURACIES = new double[]{
            1e-1,
            1e-2,
            1e-3,
            1e-6
    };

    /*
     * TODO БАГ, КОГДА ФУНКЦИЯ МЕНЯЕТ ЗНАК НА ЗАДАННОМ ОТРЕЗКЕ
     */
    public static void main(String[] args) {
        ClassesCollectorForBenchmark<UniVariableRealFunctionIntegrator> benchmarkExecutor =
                new ClassesCollectorForEachIntegrateMethodBenchmark(
                        new IntegrateMethodBenchmarkConfigurator(
                                x -> x - Math.PI/4,
                                0,
                                Math.PI / 2,
                                ACCURACIES
                        )
                );
        benchmarkExecutor.executeBenchmarkForAllImplementations(UniVariableRealFunctionIntegrator.class);
    }
}