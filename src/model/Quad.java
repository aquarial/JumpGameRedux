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
		if (x2 < y1) {
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
		return new Point[] { new Point(x1, y1), new Point(x1, y2),
				new Point(x2, y1), new Point(y2, y2) };
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

	public Point removeOtherFromThis(Quad other, double angle) {
		if ((angle < Math.PI / 2) && (angle > -1 * Math.PI / 2)) {
			System.out.print("RIGHT"); // this.x1 from other.x2
		}

		if ((angle < Math.PI) && (angle > 0)) {
			System.out.print("UP"); // this.y1 from other.y2
		}

		if ((angle > -1 * Math.PI) && (angle < 0)) {
			System.out.print("DOWN");
		}

		if ((angle > Math.PI / 2) || (angle < -1 * Math.PI / 2)) {
			System.out.print("LEFT");
		}

		System.out.println();

		return null;
	}

}