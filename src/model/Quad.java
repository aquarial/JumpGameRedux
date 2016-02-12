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

	private float x1;
	private float y1;
	private float x2;
	private float y2;

	public Quad(float x1, float y1, float x2, float y2) {
		// Keep quads positive, helps calculating if a point is inside
		if (y2 < y1) {
			float tmp = y1;
			y1 = y2;
			y2 = tmp;
		}
		if (x2 < y1) {
			float tmp = x1;
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

	public Point removeOtherFromThis(Quad other, float angle) {
		// TODO Calculate
	}

}