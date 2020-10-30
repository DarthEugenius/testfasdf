package infrastructure;

/**
 * A superinterface for each benchmark implementation.
 *
 * @param <T> Interface or class which contains methods we gonna do benchmark on (also it,s inheritors).
 * @version 1.0
 */
public interface ClassesCollectorForBenchmark<T> {

    /**
     * This method will collect all classes, which implements/extends a given interface/class. Then, a custom benchmark
     * logic will be executed
     *
     * @param superInterface - given interface/class
     */
    void executeBenchmarkForAllImplementations(Class<T> superInterface);
}
