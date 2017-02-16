package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.main.MaximinaCreator;
import sample.main.config.IConstantConfigurate;
import sample.main.graphics.Drawer;
import sample.main.primitives.ClassCenter;

import java.util.ArrayList;


public class UIController implements IConstantConfigurate{
    //UI Elements
    @FXML
    private Button buttonStart;
    @FXML
    private TextField textFieldPointsCount;
    @FXML
    private Canvas canvas;

    private MaximinaCreator maximinaCreator;
    private GraphicsContext graphicsContext;
    private ArrayList<ClassCenter> centers;

    public void draw() {
        int pointsCount = Integer.parseInt(textFieldPointsCount.getText());
        maximinaCreator = new MaximinaCreator(pointsCount,408,562);
        centers = maximinaCreator.createCenters();
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0,STANDART_FIELD_WIDTH,STANDART_FIELD_HEIGHT);
        Drawer.drawClasters(graphicsContext,centers);
    }
}
