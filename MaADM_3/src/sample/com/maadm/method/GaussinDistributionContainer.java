package sample.com.maadm.method;

/**
 * Created by User on 28.02.2017.
 */
public class GaussinDistributionContainer {
    private static final int STANDART_POINTS_COUNT = 1000;

    // Gaussin vectors
    private double[] firstVector;
    private double[] secondVector;

    private double firstExpectedValue; // mu1
    private double secondExpectedValue; // mu2

    private double firstStandartDeviation; // sigma1
    private double secondStandartDeviation; // sigma2

    private double[] firstDensityDestribution;
    private double[] secondDensityDestribution;

    public GaussinDistributionContainer() {
        this(STANDART_POINTS_COUNT, 100, 1200, -100, 600);
    }

    public GaussinDistributionContainer(int pointsCount,double firstClassLeftBorder, double firstClassRightBorder,
                                        double secondClassLeftBorder, double secondClassRightBorder) {
        firstDensityDestribution = new double[pointsCount];
        secondDensityDestribution = new double[pointsCount];
        firstVector = MathInstruments.generateVector(pointsCount, (int) firstClassLeftBorder, (int) firstClassRightBorder);
        secondVector = MathInstruments.generateVector(pointsCount, (int) secondClassLeftBorder, (int) secondClassRightBorder);

        firstExpectedValue = MathInstruments.getVectorExpectedValue(firstVector);
        secondExpectedValue = MathInstruments.getVectorExpectedValue(secondVector);

        firstStandartDeviation = MathInstruments.getStandartDeviation(firstVector, firstExpectedValue);
        secondStandartDeviation = MathInstruments.getStandartDeviation(secondVector, secondExpectedValue);
    }

    private double getDensity(int x, double expectedValue, double standartDeviation) {
        double numerator = Math.exp((-0.5) * Math.pow( ((x - expectedValue))/standartDeviation,2 ));
        double denominator = standartDeviation*Math.sqrt(2 * Math.PI);
        return (numerator  / denominator);
    }

    public void calculateDestribution(int pointsCount) {
        for (int i = 0; i < pointsCount; i++) {
            firstDensityDestribution[i] = getDensity(i, firstExpectedValue, firstStandartDeviation);
            secondDensityDestribution[i] = getDensity(i, secondExpectedValue, secondStandartDeviation);
        }
    }

    public double[] getFirstDensityDestribution() {
        return firstDensityDestribution;
    }

    public double[] getSecondDensityDestribution() {
        return secondDensityDestribution;
    }
}
