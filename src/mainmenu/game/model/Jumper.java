package mainmenu.game.model;

import mainmenu.game.model.level.Point;

class Jumper {

	// Gravity pulls down, negative is down
	static final double GRAVITY_CONSTANT = -8f;
	static final double JUMPER_WIDTH = 1;

	private static double[] corners = new double[4];

	/**
	 * Calculates the coordinates of the jumper's corners
	 * 
	 * @param pos
	 *            Point representing position
	 * 
	 * @return A quadrilateral representing the corners.
	 */
	static double[] calculateCornersAtPosition(Point pos) {
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
	Point calculateUpdate(double deltaTime) {
		double tmpxpos = xposition + deltaTime * xvelocity;
		double tmpypos = yposition + deltaTime * yvelocity;
		return new Point(tmpxpos, tmpypos);
	}

	/**
	 * Updates jumper position based on time
	 * 
	 * @param deltaTime
	 */
	void update(double deltaTime) {
		xposition += deltaTime * xvelocity;
		yposition += deltaTime * yvelocity;
		yvelocity += deltaTime * GRAVITY_CONSTANT;
	}

	/**
	 * <tt>(0,0)</tt> is in the bottom left corner
	 * 
	 * @param xpos
	 *            X position
	 * @param ypos
	 *            Y position
	 */
	Jumper(double xpos, double ypos) {
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
	Jumper(Point position) {
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
	void setVelocityToZero() {
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
	void moveBy(double deltaX, double deltaY) {
		xposition += deltaX;
		yposition += deltaY;
	}

	/**
	 * Changes position coordinates into a Quad
	 * 
	 * @return Quad representing corners
	 */
	double[] getCurrentCorners() {
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
	Point getPosition() {
		return new Point(xposition, yposition);
	}

	/**
	 * 
	 * @param p
	 *            the x and y position to set
	 */
	void setPosition(Point p) {
		xposition = p.getX();
		yposition = p.getY();
	}

	/**
	 * @return the xposition
	 */
	double getXposition() {
		return xposition;
	}

	/**
	 * @param xposition
	 *            the x position to set
	 */
	void setXposition(double xposition) {
		this.xposition = xposition;
	}

	/**
	 * @return the yposition
	 */
	double getYposition() {
		return yposition;
	}

	/**
	 * @param yposition
	 *            the y position to set
	 */
	void setYposition(double yposition) {
		this.yposition = yposition;
	}

	/**
	 * Sets velocity based on radial coordinates
	 * 
	 * @param angle
	 * @param power
	 */
	void setAngularVelocity(double angle, double power) {
		xvelocity = (double) (Math.cos(angle) * power);
		yvelocity = -(double) (Math.sin(angle) * power);
	}

	/**
	 * @return the xvelocity
	 */
	double getXvelocity() {
		return xvelocity;
	}

	/**
	 * @param xvelocity
	 *            the x velocity to set
	 */
	void setXvelocity(double xvelocity) {
		this.xvelocity = xvelocity;
	}

	/**
	 * @return the yvelocity
	 */
	double getYvelocity() {
		return yvelocity;
	}

	/**
	 * @param yvelocity
	 *            the y velocity to set
	 */
	void setYvelocity(double yvelocity) {
		this.yvelocity = yvelocity;
	}

	/**
	 * @return the isStuck
	 */
	boolean isStuck() {
		return isStuck;
	}

	/**
	 * @param isStuck
	 *            the isStuck to set
	 */
	void setStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

}
