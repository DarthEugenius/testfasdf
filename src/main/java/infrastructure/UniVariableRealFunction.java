package infrastructure;

/**
 * A @FunctionalInterface to represent function as lambda. For example: x = log(x) can be represented as
 * x -> Math.log(x), or Math::log, as a method reference.
 *
 * @version 1.0
 */
@FunctionalInterface
public interface UniVariableRealFunction {

    double value(double x);
}
