package model;

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

}
