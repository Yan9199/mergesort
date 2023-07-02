package impl.functions;

@FunctionalInterface
public interface DoubleToIntFunction {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     * @throws IllegalArgumentException if the function argument is not between 0.0 (inclusive) and 1.0 (inclusive)
     */
    int apply(double value);
}
