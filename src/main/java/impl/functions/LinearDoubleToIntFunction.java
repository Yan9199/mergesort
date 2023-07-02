package impl.functions;

public class LinearDoubleToIntFunction implements DoubleToIntFunction {

    /**
     * The f_0 coefficient.
     */
    private final double a;

    /**
     * The f_1 coefficient.
     */
    private final double b;

    /**
     * Constructs and initializes a {@code LinearDoubleToIntFunction} with the specified coefficients.
     *
     * @param a the f_0 coefficient
     * @param b the f_1 coefficient
     */
    public LinearDoubleToIntFunction(double a, double b) {
        this.a = a; this.b = b;
    }

    @Override
    public int apply(double value) {
        if (value < 0.0 || value > 1.0) throw new IllegalArgumentException();
        return (int) Math.round(a * value + b);
    }
}
