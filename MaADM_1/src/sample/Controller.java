package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import sample.primitives.ClassCenter;
import sample.primitives.Point;

import java.util.ArrayList;
import java.util.Random;

/* Main form controller */
public class Controller {
    @FXML
    private Button drawButton;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private TextField pointsCountTextField;
    @FXML
    private TextField classesCountTextField;

    private ArrayList<Point> ArrayList;
    private ArrayList<ClassCenter> classCentersArrayList;
    private int pointsCount;
    private int classesCount;
    private int drawFieldHeight;
    private int drawFieldWidth;


    private GraphicsContext graphicsContext;


    //Initialize method
    private void initializeComponents() {
        graphicsContext = mainCanvas.getGraphicsContext2D();
        pointsCount = Integer.parseInt(pointsCountTextField.getText());
        classesCount = Integer.parseInt(classesCountTextField.getText());
        drawFieldHeight = (int) mainCanvas.getHeight();
        drawFieldWidth = (int) mainCanvas.getWidth();
        ArrayList = new ArrayList<>(pointsCount);
        classCentersArrayList = new ArrayList<>(classesCount);
        graphicsContext.clearRect(0,0,drawFieldWidth,drawFieldHeight);

    }

    //Draw points from ArrayList
    private void drawPoints() {
        int x, y;
        for (Point temp : ArrayList) {
            x = temp.getX();
            y = temp.getY();
            graphicsContext.strokeLine(x,y,x,y);
        }
    }

    //Draw claster's points
    private void drawClasters() {
        ArrayList<Point> tempArrayList;
        double x,y;
        Random random = new Random();
        for (ClassCenter tempCenter : classCentersArrayList) {
            graphicsContext.setLineWidth(2);
            tempArrayList = tempCenter.getLinkedPoints();
            graphicsContext.setStroke(tempCenter.getColor());

            for (Point point : tempArrayList) {
                x = point.getX();
                y = point.getY();

                graphicsContext.strokeLine(x,y,x,y);
            }

            graphicsContext.setStroke(Color.BLACK);
            graphicsContext.strokeRect(tempCenter.getX() - 5, tempCenter.getY() - 5, 10, 10);
        }
    }

    //Generating random points in canvas field
    private void generateRandomPoints() {
        Point generatedPoint;
        int randomX, randomY;
        Random random = new Random();

        //Create points and add them in ArrayList
        for (int i = 0; i < pointsCount; i++) {
            generatedPoint = new Point();
            randomX = random.nextInt(drawFieldHeight);
            randomY = random.nextInt(drawFieldWidth);
            generatedPoint.setX(randomX);
            generatedPoint.setY(randomY);
            ClassCenter tempCenter = getNearestCenter(generatedPoint);
            tempCenter.addLinkedPoint(generatedPoint);
            ArrayList.add(generatedPoint);
        }
    }

    //Random generating classes centers
    private void createClassesCenters() {
        ClassCenter classCenter;
        int randomX, randomY;
        Random random = new Random();
        for (int i = 0; i < classesCount; i++) {
            classCenter = new ClassCenter();
            randomX = random.nextInt(drawFieldHeight);
            randomY = random.nextInt(drawFieldWidth);
            classCenter.setX(randomX);
            classCenter.setY(randomY);
            classCentersArrayList.add(classCenter);
        }
    }

    //Get nearest class center for current point
    private ClassCenter getNearestCenter(Point point) {
        ClassCenter tempCenter = null;
        double distance = Integer.MAX_VALUE;
        for (int i = 0; i < classCentersArrayList.size(); i++) {
            double tempDistance;
            tempDistance = point.getDistance(classCentersArrayList.get(i));
            if (tempDistance < distance) {
                tempCenter = classCentersArrayList.get(i);
                distance = tempDistance;
            }
        }
        return tempCenter;
    }

    //Find "right" classes centres
    private void findRightCentres() {
        int temp = 0;
        while (temp != classCentersArrayList.size()) {
            temp = 0;
            for (ClassCenter center : classCentersArrayList) {
                if (!center.findNew()) {
                    temp++;
                }
                center.clearPoints();
            }

            for (Point point : ArrayList) {
                ClassCenter tempCenter = getNearestCenter(point);
                tempCenter.addLinkedPoint(point);
            }


        }
    }

    public void onDrawButtonClick() {
        ArrayList = null;
        initializeComponents();
        createClassesCenters();
        generateRandomPoints();
        findRightCentres();
        Thread thread = new Thread();
        drawClasters();
    }
}
