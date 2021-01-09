package infrastructure.collectors;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An infrastructural collector class.
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 * @version 1.0
 */
public class FullyImplementedTypedClassesCollector<T> extends TypedClassesCollector<T> {

    /**
     * By a given superclass or a superinterface, collects all inheritors. Note: it collects iff a class isn't abstract
     * or an interface.
     *
     * @param zuper - superclass or a superinterface
     * @return collected classes.
     */
    public List<Class<? extends T>> getClassesBySuperclassOrAnInterface(Class<T> zuper) {
        return collect(zuper).stream()
                /* Check if class isn't abstract or an interface */
                .filter(aClass -> !aClass.isInterface() && !Modifier.isAbstract(aClass.getModifiers()))
                .collect(Collectors.toList());
    }
}
