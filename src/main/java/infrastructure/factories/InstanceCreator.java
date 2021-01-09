package infrastructure.factories;

/**
 * A factory interface.
 *
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 * @version 1.0
 */
public interface InstanceCreator {

    /**
     * Creates an instance of a given class.
     * @param clazz given class.
     * @param <T> type parameter.
     * @return instance.
     */
    <T> T createInstance(Class<? extends T> clazz);
}
