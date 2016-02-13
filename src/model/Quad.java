package model;

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

	public Point removeJumperFromThis(Quad other, double xvel, double yvel) {
		double angle;
		if (xvel == 0) {
			angle = Math.signum(yvel) * Math.PI / 2;
		} else {
			angle = Math.tan(yvel / xvel);
		}

		if ((angle < Math.PI / 2) && (angle > -1 * Math.PI / 2)) {
			// System.out.print("RIGHT"); // other.x2 to this.x1
		}

		if ((angle < Math.PI) && (angle > 0)) {
			// System.out.print("UP"); // other.y2 to this.y1
		}

		if ((angle > -1 * Math.PI) && (angle < 0)) {
			// System.out.print("DOWN"); // other.y1 to this.y2
		}

		if ((angle > Math.PI / 2) || (angle < -1 * Math.PI / 2)) {
			// System.out.print("LEFT"); // other.x1 to this.x2
		}

		System.out.println();

		// intersect with TOP
		return null;
	}

	Point lowerRightCornerToTopLine(Quad other, double slope) {

		// this.y2 - other.y1 = m * ( SOLUTION - other.x2)
		double x = (this.y2 - other.y1) / slope + other.x2;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = slope * (x - other.x2) + other.y1;
			return new Point(x - other.x2, y - other.y1);
		}
		return new Point(0, 0);
	}

	Point lowerRightCornerToLeftLine(Quad other, double m) {

		// SOLUTION - other.y1 = m * (this.x1 - other.x2)
		double y = m * (this.x1 - other.x2) + other.y1;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x1;
			return new Point(x - other.x2, y - other.y1);
		}

		return new Point(0, 0);
	}

	Point lowerLeftCornerToTopLine(Quad other, double m) {

		// this.y2 - other.y1 = m * ( SOLUTION - other.x1)
		double x = (this.y2 - other.y1) / m + other.x1;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = this.y2;
			return new Point(x - other.x1, y - other.y1);
		}
		return new Point(0, 0);
	}

	Point lowerLeftCornerToRightLine(Quad other, double m) {

		// SOLUTION - other.y1 = m * (other.x2 - this.x1)
		double y = m * (this.x2 - other.x1) + other.y1;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x2;
			return new Point(x - other.x1, y - other.y1);
		}

		return new Point(0, 0);
	}

	Point upperLeftCornerToBottomLine(Quad other, double m) {

		// this.y1 = m * (SOLUTION - other.x1) + other.y2
		double x = (this.y1 - other.y2) / m + other.x1;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = this.y1;
			return new Point(x - other.x1, y - other.y2);
		}

		return new Point(0, 0);
	}

	Point upperLeftCornerToRightLine(Quad other, double m) {

		// SOLUTION = m * (this.x2 - other.x1) + other.y2
		double y = m * (this.x2 - other.x1) + other.y2;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x2;
			return new Point(x - other.x1, y - other.y2);
		}

		return new Point(0, 0);
	}

	Point upperRightCornerToBottomtLine(Quad other, double m) {

		// this.y1 - other.y2 = m * (SOLUTION - other.x2)
		double x = (this.y1 - other.y2) / m + other.x2;

		if ((this.x1 <= x) && (x <= this.x2)) {
			double y = this.y1;
			return new Point(x - other.x2, y - other.y2);
		}

		return new Point(0, 0);
	}

	Point upperRightCornerToLeftLine(Quad other, double m) {

		// YY = m * (this.x1 - other.x2) + other.y2
		double y = m * (this.x1 - other.x2) + other.y2;

		if ((this.y1 <= y) && (y <= this.y2)) {
			double x = this.x1;
			return new Point(x - other.x2, y - other.y2);
		}

		return new Point(0, 0);
	}

}