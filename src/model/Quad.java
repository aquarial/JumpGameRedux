package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quadrilateral
 * <p>
 * Models an obsticle in the game
 * 
 * @author karl
 * 
 */
public class Quad {

	private double x1;
	private double y1;
	private double x2;
	private double y2;

	/**
	 * Construct a quad from an array instead of passing doubles
	 * 
	 * @param corners Array with <b>LENGTH OF 4</b>
	 */
	public Quad(double[] corners) {
		this(corners[0], corners[1], corners[2], corners[3]);
	}

	public Quad(double x1, double y1, double x2, double y2) {
		// Keep quads positive, helps calculating if a point is inside
		if (y2 < y1) {
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		if (x2 < x1) {
			double tmp = x1;
			x1 = x2;
			x2 = tmp;
		}

		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

	}

	public Point[] toPointArray() {
		return new Point[] { new Point(x1, y1), new Point(x1, y2), new Point(x2, y1), new Point(y2, y2) };
	}

	public boolean containsQuad(Quad other) {
		//@formatter:off
		return 
				this.x1 < other.x2 && 
				this.x2 > other.x1 && 
				this.y1 < other.y2 && 
				this.y2 > other.y1;
		//@formatter:on
	}

	/**
	 * 
	 * This method answers the question:<br>
	 * <i>"I have a Jumper moving at a velocity, and I know it's going to hit
	 * this Quad. Calculate distance the Jumper will move before hitting this
	 * Quad."</i>
	 * <p>
	 * A simple idea with a very complicated implementation.
	 * <p>
	 * I first figure out what direction the Jumper is moving in (Up, Down,
	 * Left, Right). It can be moving in two at the same time too. I then call 2
	 * of 8 functions that calculate the distance from the specified corner of
	 * the Jumper to the specified edge of this Quad. Then I find the smallest
	 * movement (the first obstruction by this Quad) that is not zero (still
	 * hits this Quad).
	 * 
	 * 
	 * @param jumper
	 *            the Jumper Quad
	 * @param xvelocity
	 *            velocity of other
	 * @param yvelocity
	 *            velocity of other
	 * @return
	 */
	public Point calculatePushingOtherToThis(Quad jumper, double xvelocity, double yvelocity) {
		double angle;
		if (xvelocity == 0) {
			angle = Math.signum(yvelocity) * Math.PI / 2;
			xvelocity = 0.0001;
		} else {
			angle = Math.atan2(yvelocity, xvelocity);
		}

		double m = yvelocity / xvelocity;

		List<Point> movements = new ArrayList<Point>();

		if ((angle < Math.PI / 2) && (angle > -1 * Math.PI / 2)) {
			movements.add(upperRightCornerToLeftLine(jumper, m));
			movements.add(lowerRightCornerToLeftLine(jumper, m));
			// System.out.print("RIGHT"); // other.x2 to this.x1
		}

		if ((angle < Math.PI) && (angle > 0)) {
			movements.add(upperRightCornerToBottomtLine(jumper, m));
			movements.add(upperLeftCornerToBottomLine(jumper, m));
			// System.out.print("UP"); // other.y2 to this.y1
		}

		if ((angle > -1 * Math.PI) && (angle < 0)) {
			movements.add(lowerLeftCornerToTopLine(jumper, m));
			movements.add(lowerRightCornerToTopLine(jumper, m));
			// System.out.print("DOWN"); // other.y1 to this.y2
		}

		if ((angle > Math.PI / 2) || (angle < -1 * Math.PI / 2)) {
			movements.add(upperLeftCornerToRightLine(jumper, m));
			movements.add(lowerLeftCornerToRightLine(jumper, m));
			// System.out.print("LEFT"); // other.x1 to this.x2
		}
		//@formatter:off
		Point minMovement = movements.stream()
				.filter( (Point p) -> p.getX() != 0 || p.getY() != 0 )
				.min(new PointComparator()).get();
		//@formatter:on

		// DEBUG :
		// System.out.println(minMovement);
		// System.out.println("angl = " + (angle / Math.PI) + "pi");
		// System.out.println();
		// movements.stream()
		// // .filter( (Point p) -> p.getX() != 0 || p.getY() != 0 )
		// .forEach(System.out::println);

		// intersect with TOP
		return minMovement;
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point lowerRightCornerToTopLine(Quad other, double slope) {

		// this.y2 - other.y1 = m * ( SOLUTION - other.x2)
		double x = (this.y2 - other.y1) / slope + other.x2;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = slope * (x - other.x2) + other.y1;
			return new Point(x - other.x2, y - other.y1, "lrt");
		}
		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point lowerRightCornerToLeftLine(Quad other, double m) {

		// SOLUTION - other.y1 = m * (this.x1 - other.x2)
		double y = m * (this.x1 - other.x2) + other.y1;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x1;
			return new Point(x - other.x2, y - other.y1, "lrl");
		}

		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point lowerLeftCornerToTopLine(Quad other, double m) {

		// this.y2 - other.y1 = m * ( SOLUTION - other.x1)
		double x = (this.y2 - other.y1) / m + other.x1;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = this.y2;
			return new Point(x - other.x1, y - other.y1, "llt");
		}
		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point lowerLeftCornerToRightLine(Quad other, double m) {

		// SOLUTION - other.y1 = m * (other.x2 - this.x1)
		double y = m * (this.x2 - other.x1) + other.y1;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x2;
			return new Point(x - other.x1, y - other.y1, "llr");
		}

		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point upperLeftCornerToBottomLine(Quad other, double m) {

		// this.y1 = m * (SOLUTION - other.x1) + other.y2
		double x = (this.y1 - other.y2) / m + other.x1;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = this.y1;
			return new Point(x - other.x1, y - other.y2, "ulb");
		}

		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point upperLeftCornerToRightLine(Quad other, double m) {

		// SOLUTION = m * (this.x2 - other.x1) + other.y2
		double y = m * (this.x2 - other.x1) + other.y2;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x2;
			return new Point(x - other.x1, y - other.y2, "ulr");
		}

		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point upperRightCornerToBottomtLine(Quad other, double m) {

		// this.y1 - other.y2 = m * (SOLUTION - other.x2)
		double x = (this.y1 - other.y2) / m + other.x2;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = this.y1;
			return new Point(x - other.x2, y - other.y2, "urb");
		}

		return new Point(0, 0);
	}

	/**
	 * Calculates the movement from the corner of <code>other</code> specified
	 * in the name to push it up against the specified line of <code>this</code>
	 * .
	 * <p>
	 * Returns <tt>(0, 0)</tt> if other can't be pushed against
	 * <code>this</code> at the velocity it's going at.
	 * 
	 * @param other
	 * @param slope
	 * @return Point representing movement
	 * @return (0, 0) if can't be pushed against this
	 */
	Point upperRightCornerToLeftLine(Quad other, double m) {

		// YY = m * (this.x1 - other.x2) + other.y2
		double y = m * (this.x1 - other.x2) + other.y2;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x1;
			return new Point(x - other.x2, y - other.y2, "url");
		}

		return new Point(0, 0);
	}

	/**
	 * @return the x1
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * @return the y1
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * @return the x2
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * @return the y2
	 */
	public double getY2() {
		return y2;
	}

}