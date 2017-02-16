package sample.main.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.main.primitives.ClassCenter;
import sample.main.primitives.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 14.02.2017.
 */
public class Drawer {
    public static void drawClasters(GraphicsContext graphicsContext,ArrayList<ClassCenter> classCentersArrayList) {
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
}
