package entity;

/**
 * A {@code @FunctionalInterface} to represent function as a lambda expression. For example: <em>x = log(x)</em>
 * can be represented as {@code x -> Math.log(x)}, or {@code Math::log), as a method reference.
 *
 * @version 1.0
 */
@FunctionalInterface
public interface UniVariableRealFunction {

    double value(double x);
}