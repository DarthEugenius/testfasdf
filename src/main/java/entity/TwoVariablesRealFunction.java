package entity;

/**
 * @author Evgeniy Ternovoy
 * @author Mihail Goncharov
 */
@FunctionalInterface
public interface TwoVariablesRealFunction {
    double value(double x, double y);
}
