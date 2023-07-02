package impl.functions;

import java.util.List;

public class ConstantListToIntFunction<T> implements ListToIntFunction<T> {

    /**
     * The constant value used by this function.
     */
    private final int value;

    /**
     * Constructs and initializes a {@code ConstantListToIntFunction} with the specified constant value.
     *
     * @param value the constant value used by the function
     */
    public ConstantListToIntFunction(int value) {
        this.value = value;
    }

    @Override
    public int apply(List<T> elements) {
        return value;
    }
}
