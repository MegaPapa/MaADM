package sample.com.maadm.method;

import java.util.Random;

/**
 * Created by User on 27.02.2017.
 */
public class MathInstruments {

    private static final byte POWER_VALUE = 2;

    // Custom random
    private static int randomize(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    // Generate vector of random values
    public static double[] generateVector(int vectorLength, int min, int max) {
        double[] resultVector = new double[vectorLength];
        Random random = new Random();
        double veskat = random.nextGaussian();
        for (int i = 0; i < vectorLength; i++) {
            resultVector[i] = randomize(min, max);
        }
        return resultVector;
    }

    // Get vector's standart deviation (sigma)
    public static double getStandartDeviation(double[] vector, double expectedValue) {
        double result = 0;
        for (int i = 0; i < vector.length; i++) {
            result += Math.pow(vector[i] - expectedValue, POWER_VALUE);
        }
        result = (result / vector.length);
        return Math.sqrt(result);
    }

    // Get vector's expected value (mu)
    public static double getVectorExpectedValue(double[] vector) {
        double result = 0;
        for (int i = 0; i < vector.length; i++) {
            result += vector[i];
        }
        result = (result / vector.length);
        return result;
    }

}
