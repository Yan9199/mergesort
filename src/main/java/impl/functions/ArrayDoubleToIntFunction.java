package impl.functions;

/**
 * Represents a function that accepts a double-valued argument and produces an int-valued result.
 *
 * <p>The function values can be calculated as follows:
 * <ul>
 *   <li>If {@code x * (n - 1)} has a maximum deviation of {@value DELTA} from an integer, the element is returned at the index
 *   {@code x * (n - 1)}</li>
 *   <li>Otherwise, the indices {@code x * (n - 1)} (rounded down) and {@code x * (n - 1)} (rounded up) are linearly
 *   interpolated and rounded to an integer.</li>
 * </ul>
 *
 * @author Nhan Huynh
 */
public class ArrayDoubleToIntFunction implements DoubleToIntFunction {
    /**
     * THe maximum deviation of the function (1.0e-6 = 1.0 x 10-6 = 0.0000010)..
     */
    private static final double DELTA = 1.0e-6;

    /**
     * The array of values used for this function.
     */
    private final int[] elements;

    /**
     * Constructs and initializes an {@code ArrayDoubleToIntFunction} with the given values used for the function.
     *
     * @param elements the array of values used for the function
     */
    public ArrayDoubleToIntFunction(int[] elements) {
        this.elements = new int[elements.length];
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }

    /**
     * Applies this function to the given argument.
     *
     * <p>If {@code x * (n - 1)} has a maximum deviation of 10^-6 from an integer, the element is returned at its index.
     * Otherwise, the indices {@code x * (n - 1)} (rounded down) and {@code x * (n - 1)} (rounded up) are linearly interpolated
     * and rounded to an integer.
     *
     * @param value the function argument
     * @return the function result
     * @throws IllegalArgumentException if the function argument is not between 0.0 (inclusive) and 1.0 (inclusive)
     */
    @Override
    public int apply(double value) {
        if (value < 0.0 || value > 1.0) throw new IllegalArgumentException();
        final int len = elements.length; final double index = value * (len - 1); final int i = (int) Math.round(index);
        if (Math.abs(index - i) <= DELTA) return elements[i];
        final int first = (int) index, second = (int) Math.ceil(index), lower = elements[first], upper = elements[second];
        return (int) Math.round(lower + ((double) (upper - lower) / (second - first)) * (index - first));
    }
}
