package model;

public class Jumper {

	// Gravity pulls down, negative is down
	public static final double GRAVITY_CONSTANT = -8f;
	public static final double JUMPER_WIDTH = 1;

	private static double[] corners = new double[4];

	/**
	 * Calculates the coordinates of the jumper's corners
	 * 
	 * @param pos
	 *            Point representing position
	 * 
	 * @return A quadrilateral representing the corners.
	 */
	public static double[] calculateCornersAtPosition(Point pos) {
		double radius = JUMPER_WIDTH / 2;
		corners[0] = pos.getX() - radius;
		corners[1] = pos.getY() - radius;
		corners[2] = pos.getX() + radius;
		corners[3] = pos.getY() + radius;
		return corners;
	}

	private double xposition;
	private double yposition;
	private double xvelocity;
	private double yvelocity;
	private boolean isStuck;

	/**
	 * Calculates a potential update
	 * <p>
	 * Does <b>NOT</b> change state
	 * 
	 * @param deltaTime
	 * @return new x and y positions.
	 */
	public Point calculateUpdate(double deltaTime) {
		double tmpxpos = xposition + deltaTime * xvelocity;
		double tmpypos = yposition + deltaTime * yvelocity;
		return new Point(tmpxpos, tmpypos);
	}

	/**
	 * Updates jumper position based on time
	 * 
	 * @param deltaTime
	 */
	public void update(double deltaTime) {
		xposition += deltaTime * xvelocity;
		yposition += deltaTime * yvelocity;
		// if (yvelocity > -5) {
		yvelocity += deltaTime * GRAVITY_CONSTANT;
		// }
	}

	/**
	 * <tt>(0,0)</tt> is in the bottom left corner
	 * 
	 * @param xpos
	 *            X position
	 * @param ypos
	 *            Y position
	 */
	public Jumper(double xpos, double ypos) {
		this.xposition = xpos;
		this.yposition = ypos;
		this.xvelocity = 0;
		this.yvelocity = 0;
		this.isStuck = false;
	}

	/**
	 * <tt>(0,0)</tt> is in the bottom left corner
	 * 
	 * @param xpos
	 *            X position
	 * @param ypos
	 *            Y position
	 */
	public Jumper(Point position) {
		this.xposition = position.getX();
		this.yposition = position.getY();
		this.xvelocity = 0;
		this.yvelocity = 0;
	}

	/**
	 * Stops momentum
	 * <p>
	 * Sets both velocities to zero
	 */
	public void setVelocityToZero() {
		xvelocity = 0;
		yvelocity = 0;
	}

	/**
	 * Moves player position by arguments.
	 * <p>
	 * Use <code>update(deltaTime)</code> to update position by current velocity
	 * 
	 * @param deltaX
	 * @param deltaY
	 * 
	 * @see update(double deltaTime)
	 */
	public void moveBy(double deltaX, double deltaY) {
		xposition += deltaX;
		yposition += deltaY;
	}

	/**
	 * Changes position coordinates into a Quad
	 * 
	 * @return Quad representing corners
	 */
	public double[] getCurrentCorners() {
		double x1, x2, y1, y2, radius;
		radius = JUMPER_WIDTH / 2;
		x1 = this.xposition - radius;
		x2 = this.xposition + radius;
		y1 = this.yposition - radius;
		y2 = this.yposition + radius;
		return new double[] { x1, y1, x2, y2 };
	}

	/**
	 * 
	 * @return p the x and y position
	 */
	public Point getPosition() {
		return new Point(xposition, yposition);
	}

	/**
	 * 
	 * @param p
	 *            the x and y position to set
	 */
	public void setPosition(Point p) {
		xposition = p.getX();
		yposition = p.getY();
	}

	/**
	 * @return the xposition
	 */
	public double getXposition() {
		return xposition;
	}

	/**
	 * @param xposition
	 *            the x position to set
	 */
	public void setXposition(double xposition) {
		this.xposition = xposition;
	}

	/**
	 * @return the yposition
	 */
	public double getYposition() {
		return yposition;
	}

	/**
	 * @param yposition
	 *            the y position to set
	 */
	public void setYposition(double yposition) {
		this.yposition = yposition;
	}

	/**
	 * Sets velocity based on radial coordinates
	 * 
	 * @param angle
	 * @param power
	 */
	public void setAngularVelocity(double angle, double power) {
		xvelocity = (double) (Math.cos(angle) * power);
		yvelocity = -(double) (Math.sin(angle) * power);
	}

	/**
	 * @return the xvelocity
	 */
	public double getXvelocity() {
		return xvelocity;
	}

	/**
	 * @param xvelocity
	 *            the x velocity to set
	 */
	public void setXvelocity(double xvelocity) {
		this.xvelocity = xvelocity;
	}

	/**
	 * @return the yvelocity
	 */
	public double getYvelocity() {
		return yvelocity;
	}

	/**
	 * @param yvelocity
	 *            the y velocity to set
	 */
	public void setYvelocity(double yvelocity) {
		this.yvelocity = yvelocity;
	}

	/**
	 * @return the isStuck
	 */
	public boolean isStuck() {
		return isStuck;
	}

	/**
	 * @param isStuck
	 *            the isStuck to set
	 */
	public void setStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

}
