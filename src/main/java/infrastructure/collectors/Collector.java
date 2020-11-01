package infrastructure.collectors;

import java.util.Set;

/**
 * Root interface for each collecting class.
 *
 * @param <C> type of collected entities.
 * @param <A> type of attribute.
 * @version 1.0
 */
public interface Collector<C, A> {

    /**
     * This method collects all entities, having a specific attribute.
     *
     * @param attribute given attribute.
     * @return set of collected entities.
     */
    Set<C> collect(A attribute);
}
