package impl.functions;

public class LinearInterpolation implements DoubleToIntFunctionFitter {

    @Override
    public DoubleToIntFunction fitFunction(Integer[] y) {
        final int len = y.length;
        final double[] array = new double[len];
        final int[] a = new int[len];
        for (int i = 0; i < len; ) {
            final Integer n = y[i];
            if (n != null) {
                array[i] = n;
                a[i] = n;
                i++;
                continue;
            }
            final int lowerBound = i - 1, upperBound = getUpperBound(y, i + 1);
            assignToArrays(array, a, lowerBound, upperBound, y[lowerBound], y[upperBound]);
            i = upperBound;
        }
        return new ArrayDoubleToIntFunction(a);
    }

    private void assignToArrays(double[] array, int[] a, int lowerBound, int upperBound, int lower, int upper) {
        for (int i = lowerBound + 1; i < upperBound; i++) {
            final double value = lower + ((double) (upper - lower) / (upperBound - lowerBound)) * (i - lowerBound);
            array[i] = value;
            a[i] = (int) Math.round(value);
        }
    }

    private int getUpperBound(Integer[] y, int index) {
        for (; ; index++) if (y[index] != null) return index;
    }
}
