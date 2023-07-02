package impl.functions;

import java.util.Comparator;
import java.util.List;

public class FunctionOnRatioOfRuns<T> extends FunctionOnDegreeOfDisorder<T> {

    /**
     * The function to be applied to the ratio of runs.
     */
    private final DoubleToIntFunction function;

    /**
     * Constructs and initializes a {@code FunctionOnRatioOfRuns}.
     *
     * @param cmp      the comparator used to compare the elements of the list
     * @param function the function to be applied to the ratio of runs
     */
    public FunctionOnRatioOfRuns(Comparator<? super T> cmp, DoubleToIntFunction function) {
        super(cmp); this.function = function;
    }

    @Override
    public int apply(List<T> elements) {
        if (elements == null) throw new NullPointerException();
        int size = elements.size(), runs = 1, index = 1;
        for (T current = elements.get(0); index < size; index++) {T next = elements.get(index); if (cmp.compare(current, next) > 0) runs++; current = next;}
        return function.apply((double) runs / size);
    }
}
