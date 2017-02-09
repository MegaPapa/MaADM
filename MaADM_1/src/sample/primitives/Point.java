package sample.primitives;

/**
 * Created by User on 09.02.2017.
 */
public class Point {
    private int x;
    private int y;

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
}
