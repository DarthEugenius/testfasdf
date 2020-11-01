package infrastructure.collectors;

import org.reflections.Reflections;

import java.util.Set;

/**
 * @param <A> typed parameter.
 * @version 1.0
 */
public abstract class TypedClassesCollector<A> implements Collector<Class<? extends A>, Class<A>> {

    /**
     * Method collects and returns all classes and interfaces, which are inherited from a give one.
     * {@code Reflections} scans all packages ro find them.
     *
     * @param attribute - given class or an interface.
     * @return set of classes and interfaces.
     */
    @Override
    public Set<Class<? extends A>> collect(Class<A> attribute) {
        return (new Reflections("")).getSubTypesOf(attribute);
    }
}
