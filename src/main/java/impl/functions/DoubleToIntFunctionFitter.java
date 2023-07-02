package impl.functions;

public interface DoubleToIntFunctionFitter {

    /**
     * Fits a function to the given data.
     *
     * @param y the samples
     * @return the fitted function
     */
    DoubleToIntFunction fitFunction(Integer[] y);
}
