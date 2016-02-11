package model;

public class Jumper {

	private float xpos;

	private float ypos;

	public Jumper(float xpos, float ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}

	public Point getPosition() {
		return new Point(xpos, ypos);
	}

	public void setPosition(Point p) {
		xpos = p.getX();
		ypos = p.getY();
	}

	public float getXpos() {
		return xpos;
	}

	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	public float getYpos() {
		return ypos;
	}

	public void setYpos(float ypos) {
		this.ypos = ypos;
	}

}
