package model;

import java.util.List;

public class MainModel {

	private List<Quad> stickyBlocks;
	private Jumper jumper;

	public MainModel(Point playerLocation, List<Quad> obsticales) {
		this.jumper = new Jumper(playerLocation);
		this.stickyBlocks = obsticales;
	}

	public void update(float deltaTime) {
		Point newPosition = jumper.calculateUpdate(deltaTime);

		Quad jumperQuad = Jumper.calculateQuadAtPosition(newPosition);
		Point[] corners = jumperQuad.toPointArray();

		Point[] newJumperPositions = new Point[4];
		for (int index = 0; index < 4; index++) {
			Point corner = corners[index];
			
		}
	}

	public float jumperXPos() {
		return jumper.getXposition();
	}

	public float jumperYPos() {
		return jumper.getYposition();
	}

}
