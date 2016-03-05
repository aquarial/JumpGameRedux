package mainmenu.game;

public class Quad {

	protected double x1;
	protected double y1;
	protected double x2;
	protected double y2;

	
	/**
	 * Default constructor
	 */
	public Quad() {
	}
	
	/**
	 * Construct a quad from an array instead of passing doubles
	 * 
	 * @param corners
	 *            Array with <b>LENGTH OF 4</b>
	 */
	public Quad(double[] corners) {
		this(corners[0], corners[1], corners[2], corners[3]);
	}

	/**
	 * Construct a quad from each value separately
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Quad(double x1, double y1, double x2, double y2) {
		// Keep quad positive, helps calculating if a point is inside
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
