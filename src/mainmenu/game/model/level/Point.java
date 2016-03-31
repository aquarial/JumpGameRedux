package mainmenu.game.model.level;

/**
 * Represents a point
 * <p>
 * Wraps x and y value
 * 
 * @author karl
 * 
 */
public class Point {

    private double x;
    private double y;
    private String debug = "";

    public static final Point ZERO = new Point(0, 0);

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, String d) {
        this.x = x;
        this.y = y;
        debug = d;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")     " + debug;
    }

    public boolean equalsPoint(Point other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    public boolean lessThan(Point other) {
        return Double.compare(
                Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2),
                Math.pow(other.getX(), 2) + Math.pow(other.getY(), 2)) < 0;
    }
}
