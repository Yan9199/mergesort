package impl.functions;

public class LinearRegression implements DoubleToIntFunctionFitter {

    @Override
    public DoubleToIntFunction fitFunction(Integer[] y) {
        int samples = 0, index = 0;
        double sumX = 0, sumY = 0, d = y.length - 1;
        for (Integer i : y) {
            if (i == null) {
                index++;
                continue;
            }
            samples++;
            sumX += index / d;
            sumY += i;
            index++;
        }
        double averageX = sumX / samples, averageY = sumY / samples, numerator = 0.0, denominator = 0.0;
        index = 0;
        for (Integer i : y) {
            if (i == null) {
                index++;
                continue;
            }
            final double value = index / d - averageX;
            numerator += value * (i - averageY);
            denominator += Math.pow(value, 2);
            index++;
        }
        final double b = numerator / denominator;
        return new LinearDoubleToIntFunction(b, averageY - b * averageX);
    }
}
