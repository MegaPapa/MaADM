package sample.main.primitives;

import javafx.scene.paint.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 13.02.2017.
 */
public class ClassCenter extends Point {
    private Color color;
    private ArrayList<Point> linkedPoints = new ArrayList<Point>();

    public ClassCenter() {
        //Create class color
        Random random = new Random();
        float r,g,b;
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();
        color = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    public void addLinkedPoint(Point point) {
        linkedPoints.add(point);
    }

    public void clearPoints() {
        linkedPoints.clear();
    }

    //Find new class center
    public boolean findNew() {
        long tempX = 0;
        long tempY = 0;
        for (Point tempPoint : linkedPoints) {
            tempX += tempPoint.getX();
            tempY += tempPoint.getY();
        }
        tempX /= linkedPoints.size();
        tempY /= linkedPoints.size();
        boolean result = false;
        if ((tempX != this.getX()) || (tempY != this.getY())) {
            result = true;
        }
        this.setX((int)tempX);
        this.setY((int)tempY);
        return result;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<Point> getLinkedPoints() {
        return linkedPoints;
    }
}
