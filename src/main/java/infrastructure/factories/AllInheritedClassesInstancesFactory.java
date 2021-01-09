package infrastructure.factories;

import infrastructure.collectors.FullyImplementedTypedClassesCollector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A factory class, which can create instances out of classes, inherited from a given class or an interface.
 *
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 */
public class AllInheritedClassesInstancesFactory implements InstanceCreator {

    /**
     * By a given class, creates instances for all it's inheritors.
     */
    public <T> List<T> getClassesInstancesBySuperClassOrInterface(Class<T> zuper) {

        final FullyImplementedTypedClassesCollector<T> collector = new FullyImplementedTypedClassesCollector<>();

        return collector.getClassesBySuperclassOrAnInterface(zuper).stream()
                .map(this::createInstance).collect(Collectors.toList());
    }

    @Override
    public <T> T createInstance(Class<? extends T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
