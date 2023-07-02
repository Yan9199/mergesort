package impl.functions;

import java.util.Comparator;

public abstract class FunctionOnDegreeOfDisorder<T> implements ListToIntFunction<T> {

    /**
     * The comparator used to compare the elements of the list.
     */
    protected final Comparator<? super T> cmp;

    /**
     * Constructs and initializes a {@code FunctionOnDegreeOfDisorder} with the specified comparator.
     *
     * @param cmp the comparator used to compare the elements of the list
     */
    public FunctionOnDegreeOfDisorder(Comparator<? super T> cmp) {
        this.cmp = cmp;
    }
}
