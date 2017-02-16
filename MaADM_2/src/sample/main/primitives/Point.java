package sample.main.primitives;

/**
 * Created by User on 13.02.2017.
 */
public class Point {
    private int x;
    private int y;
    private int distanceToCenter;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this(0,0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //Get distance from THIS point to second point
    public int getDistance(Point secondPoint) {
        int distance;
        distance = (int) (Math.pow((this.x - secondPoint.getX()),2) + Math.pow((this.y - secondPoint.getY()),2));
        distance = (int) Math.sqrt(distance);
        return distance;
    }

    public int getDistanceToCenter() {
        return distanceToCenter;
    }

    public void setDistanceToCenter(int distanceToCenter) {
        this.distanceToCenter = distanceToCenter;
    }
}
