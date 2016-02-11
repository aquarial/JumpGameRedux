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

	boolean containsPoint(Point p) {
		if (p.getX() < x1 || p.getX() > x2) {
			return false;
		}
		if (p.getY() < y1 || p.getY() > y2) {
			return false;
		}

		return true;
	}

}