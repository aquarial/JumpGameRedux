package model;

import java.util.ArrayList;
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
		Quad currentJumperQuad = jumper.positionToQuad();
		Quad futureJumperQuad = Jumper.calculateQuadAtPosition(newPosition);

		ArrayList<Point> minMovements = new ArrayList<Point>();
		for (Quad blockade : stickyBlocks) {
			if (blockade.containsQuad(futureJumperQuad)) {

				double x = jumper.getXvelocity();
				double y = jumper.getYvelocity();
				Point newMinMovement = blockade.calculatePushingOtherToThis(currentJumperQuad, x, y);
				minMovements.add(newMinMovement);

			}
		}

		if (minMovements.size() > 0) {
			Point minimumMove = minMovements.stream().min(new PointComparator()).get();
			jumper.setVelocityToZero();
			jumper.moveBy(minimumMove.getX(), minimumMove.getY());
		}

	}

	public double jumperXPos() {
		return jumper.getXposition();
	}

	public double jumperYPos() {
		return jumper.getYposition();
	}

}
