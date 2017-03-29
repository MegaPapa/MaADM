package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import sample.com.maadm.method.GaussinDistributionContainer;

import java.util.HashMap;
import java.util.Random;

public class Controller {

    // UI elements
    @FXML
    private LineChart<Double,Double> gaussinChart;
    @FXML
    private TextField firstClassProbabilityTextField;
    @FXML
    private TextField secondClassProbabilityTextField;
    @FXML
    private TextField firstClassLeftBordedTextField;
    @FXML
    private TextField firstClassRightBordedTextField;
    @FXML
    private TextField secondClassLeftBordedTextField;
    @FXML
    private TextField secondClassRightBordedTextField;
    //--------------------------------------------------

    private static final byte RESULT_VALUES_LENGTH = 6;
    private static final int POINTS_COUNT = 1000;

    /*
            Structure of return values:
        result[0] - first class probability
        result[1] - second class probability
        result[2] - first gaussin vector left border
        result[3] - first gaussin vector right border
        result[4] - second gaussin vector left border
        result[5] - second gaussin vector right border
    */
    private double[] getValuesFromUI() {
        double[] result = null;
        try {
            result = new double[RESULT_VALUES_LENGTH];
            result[0] = Double.parseDouble(firstClassProbabilityTextField.getText());
            result[1] = Double.parseDouble(secondClassProbabilityTextField.getText());
            result[2] = Double.parseDouble(firstClassLeftBordedTextField.getText());
            result[3] = Double.parseDouble(firstClassRightBordedTextField.getText());
            result[4] = Double.parseDouble(secondClassLeftBordedTextField.getText());
            result[5] = Double.parseDouble(secondClassRightBordedTextField.getText());
        }
        catch (ClassCastException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void drawChart() {
        gaussinChart.getData().clear();
        double[] values = getValuesFromUI();
        GaussinDistributionContainer container = new GaussinDistributionContainer(POINTS_COUNT, values[2], values[3],
                                                                                    values[4], values[5]);
        container.calculateDestribution(POINTS_COUNT);
        //--------------------------------------------------------------
        Random random = new Random();
        double delimeter = 0;
        double[] tmp_1 = container.getFirstDensityDestribution();
        double[] tmp_2 = container.getSecondDensityDestribution();
        double min = 1;
        double minY = 0;
        double firstClassSquare = 0;
        double secondClassSquare = 0;
        double firstPercent = values[0];
        double secondPercent = 1 - firstPercent;

        HashMap<Integer, Double> firstClass = new HashMap<Integer, Double>();
        HashMap<Integer, Double> secondClass = new HashMap<Integer, Double>();

        //Вынести в drawer
        XYChart.Series<Double, Double> series = new XYChart.Series<Double, Double>();
        XYChart.Series<Double, Double> secondSeries = new XYChart.Series<Double, Double>();
        XYChart.Series<Double, Double> delimeterSession = new XYChart.Series<Double, Double>();

        for (int i = 0; i < POINTS_COUNT; i++) {
            double y_1 = tmp_1[i] * firstPercent; // replace 0.5 to value from ui
            firstClass.put(i, y_1); // create first class points
            series.getData().add(new XYChart.Data(i ,y_1));
            double y_2 = tmp_2[i] * secondPercent; // replace 0.5 to value from ui
            secondSeries.getData().add(new XYChart.Data(i  ,y_2));
            secondClass.put(i, y_2); // create second class points
            firstClassSquare += y_1; // calculate first class square
            secondClassSquare += y_2; // calculate second class square

            //Find delimiter point
            if (Math.abs(y_1 - y_2) < min) {
                min = Math.abs(y_1 - y_2);
                minY = y_1;
                delimeter = i;
            }
        }

        // find error
        double firstErrorSquare = 0;
        for (int i = (int)delimeter; i < firstClass.size(); i++) {
            firstErrorSquare += firstClass.get(i);
        }

        double secondErrorSquare = 0;
        for (int i = 0; i < delimeter; i++) {
            secondErrorSquare += secondClass.get(i);
        }

        delimeterSession.getData().add(new XYChart.Data(delimeter ,minY));
        //draw series
        gaussinChart.getData().add(series);
        gaussinChart.getData().add(secondSeries);
        gaussinChart.getData().add(delimeterSession);
        //end drawing

        // Out of values
        System.out.println("Delimeter: " + delimeter);
        System.out.println("False alarm square: " + secondErrorSquare + "; Error skiping: " + firstErrorSquare);
        System.out.println("First Square: " + firstClassSquare + "; Second Square: " + secondClassSquare);
        System.out.println("Summary probability: " + (firstClassSquare + secondClassSquare));
    }

}
