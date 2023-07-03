package impl.functions;

import java.util.Comparator;
import java.util.List;

public class FunctionOnRatioOfInversions<T> extends FunctionOnDegreeOfDisorder<T> {

    /**
     * The function to be applied to the ratio of runs.
     */
    private final DoubleToIntFunction function;

    /**
     * Constructs and initializes a {@code FunctionOnRatioOfInversions}.
     *
     * @param cmp      the comparator used to compare the elements of the list
     * @param function the function to be applied to the ratio of runs
     */
    public FunctionOnRatioOfInversions(Comparator<? super T> cmp, DoubleToIntFunction function) {
        super(cmp);
        this.function = function;
    }

    @Override
    public int apply(List<T> elements) {
        if (elements == null) throw new NullPointerException();
        int size = elements.size(), maxInversions = (size - 1) * size / 2, inversions = 0;
        for (int i = 0; i < size; i++) {
            T current = elements.get(i);
            for (int index = i + 1; index < size; index++)
                if (cmp.compare(current, elements.get(index)) > 0) inversions++;
        }
        return function.apply((double) inversions / maxInversions);
    }
}
