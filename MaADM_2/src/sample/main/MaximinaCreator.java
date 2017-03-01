package sample.main;

import sample.main.config.IConstantConfigurate;
import sample.main.primitives.ClassCenter;
import sample.main.primitives.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 13.02.2017.
 */
public class MaximinaCreator implements IConstantConfigurate {

    private int pointsCount;
    private int drawFieldHeight,
                drawFieldWidth;
    private ArrayList<ClassCenter> classesCenters;
    private ArrayList<Point> points;

    public MaximinaCreator() {
        this(STANDART_POINTS_COUNT, STANDART_FIELD_HEIGHT, STANDART_FIELD_WIDTH);
    }

    public MaximinaCreator(int pointsCount,int drawFieldHeight,int drawFieldWidth) {
        this.drawFieldHeight = drawFieldHeight;
        this.drawFieldWidth = drawFieldWidth;
        this.pointsCount = pointsCount;
        points = new ArrayList<Point>(pointsCount);
        classesCenters = new ArrayList<ClassCenter>();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    //Generate random points for draw field
    private void generateRandomPoints() {
        Point randomPoint;
        int randomX,randomY;
        Random random = new Random();

        for (int i = 0; i < pointsCount; i++) {
            randomX = random.nextInt(drawFieldWidth);
            randomY = random.nextInt(drawFieldHeight);
            randomPoint = new Point(randomX,randomY);
            points.add(randomPoint);
        }
    }

    //Get nearest class center for current point
    private ClassCenter getNearestCenter(Point point) {
        ClassCenter tempCenter = null;
        double distance = Integer.MAX_VALUE;
        for (int i = 0; i < classesCenters.size(); i++) {
            double tempDistance;
            tempDistance = point.getDistance(classesCenters.get(i));
            if (tempDistance < distance) {
                tempCenter = classesCenters.get(i);
                distance = tempDistance;
            }
        }
        if (distance != Integer.MAX_VALUE) {
            point.setDistanceToCenter((int)distance);
        }
        return tempCenter;
    }

    private void turnPointToClassCenter(int index) {
        Point tmpPoint = points.get(index);
        ClassCenter newCenter = new ClassCenter();
        newCenter.setX(tmpPoint.getX());
        newCenter.setY(tmpPoint.getY());
        classesCenters.add(newCenter);
        points.remove(index);
    }

    /* Created first and second claster center */
    //First center
    private void createFirstCenter() {
        turnPointToClassCenter(FIRST_POINT_INDEX);
    }

    //Second center
    private void createSecondCenter() {
        int maxDistance = MIN_VALUE;
        int currentDistance = MIN_VALUE;
        int mostDistantPointIndex = MIN_VALUE;
        Point tmpPoint = null;
        ClassCenter classCenter = classesCenters.get(FIRST_POINT_INDEX);

        for (int i = 0; i < points.size(); i++) {
            tmpPoint = points.get(i);
            if (tmpPoint != null) {
                currentDistance = classCenter.getDistance(tmpPoint);
            }
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                mostDistantPointIndex = i;
            }
        }
        if (mostDistantPointIndex != MIN_VALUE) {
            turnPointToClassCenter(mostDistantPointIndex);
        }
    }

    private double getAverageDistance() {
        double average = 0;
        int counter = 0;
        for (ClassCenter centerOne : classesCenters) {
            for (ClassCenter centerTwo : classesCenters) {
                if (centerOne != centerTwo) {
                    average += centerOne.getDistance(centerTwo);
                    counter++;
                }
            }
        }

        average /= counter;
        return average;
    }

    public ArrayList<ClassCenter> createCenters() {
        generateRandomPoints();
        createFirstCenter();
        createSecondCenter();
        boolean check = true;
        while (check) {

            for (ClassCenter center : classesCenters) {
                center.clearPoints();
            }

            for (Point point : points) {
                ClassCenter temp = getNearestCenter(point);
                temp.addLinkedPoint(point);
            }


            int average = (int)getAverageDistance();
            average /= 2;
            Point newCenter = null;
            for (Point point : points) {
                if (point.getDistanceToCenter() > average) {
                    newCenter = point;
                }
            }

            if (newCenter != null) {
                ClassCenter center = new ClassCenter();
                center.setX(newCenter.getX());
                center.setY(newCenter.getY());
                classesCenters.add(center);

                points.remove(newCenter);

            }
            else {
                check = false;
            }
        }
        return classesCenters;
    }

}
