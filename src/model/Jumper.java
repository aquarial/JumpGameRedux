package model;

public class Jumper {

	// Gravity pulls down, negative is down
	public static final float GRAVITY_CONSTANT = -0.1f;

	private float xposition;
	private float yposition;
	private float xvelocity;
	private float yvelocity;

	/**
	 * Updates jumper position based on time
	 * 
	 * @param deltaTime
	 */
	public void update(float deltaTime) {
		xposition += deltaTime * xvelocity;
		yposition += deltaTime * yvelocity;
		yvelocity += GRAVITY_CONSTANT;
	}

	/**
	 * <tt>(0,0)</tt> is in the bottom left corner
	 * 
	 * @param xpos
	 *            X position
	 * @param ypos
	 *            Y position
	 */
	public Jumper(float xpos, float ypos) {
		this.xposition = xpos;
		this.yposition = ypos;
		this.xvelocity = 0;
		this.yvelocity = 0;
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
	 * @see update(float deltaTime)
	 */
	public void moveBy(float deltaX, float deltaY) {
		xposition += deltaX;
		yposition += deltaY;
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
	public float getXposition() {
		return xposition;
	}

	/**
	 * @param xposition
	 *            the x position to set
	 */
	public void setXposition(float xposition) {
		this.xposition = xposition;
	}

	/**
	 * @return the yposition
	 */
	public float getYposition() {
		return yposition;
	}

	/**
	 * @param yposition
	 *            the y position to set
	 */
	public void setYposition(float yposition) {
		this.yposition = yposition;
	}

	/**
	 * Sets velocity based on radial coordinates
	 * 
	 * @param radianAngle
	 * @param radius
	 */
	public void setAngularVelocity(float radianAngle, float radius) {
		xvelocity = (float) (Math.cos(radianAngle) * radius);
		yvelocity = (float) (Math.sin(radianAngle) * radius);
	}

	/**
	 * @return the xvelocity
	 */
	public float getXvelocity() {
		return xvelocity;
	}

	/**
	 * @param xvelocity
	 *            the x velocity to set
	 */
	public void setXvelocity(float xvelocity) {
		this.xvelocity = xvelocity;
	}

	/**
	 * @return the yvelocity
	 */
	public float getYvelocity() {
		return yvelocity;
	}

	/**
	 * @param yvelocity
	 *            the y velocity to set
	 */
	public void setYvelocity(float yvelocity) {
		this.yvelocity = yvelocity;
	}

}
