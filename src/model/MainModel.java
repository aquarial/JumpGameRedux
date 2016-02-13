package model;

import java.util.ArrayList;
import java.util.List;

public class MainModel {

	private List<Quad> stickyQuads;
	private Jumper jumper;

	public MainModel(Level level) {
		this(level.getPlayerPosition(), level.getStickyQuads());
	}

	public MainModel(Point playerLocation, List<Quad> obsticales) {
		this.jumper = new Jumper(playerLocation);
		this.stickyQuads = obsticales;
	}

	/**
	 * Updates the model based on time passed. Moves the Jumper/Player.
	 * 
	 * @param deltaTime
	 */
	public void updateModel(double deltaTime) {

		if (!jumper.isStuck()) {
			Point newPosition = jumper.calculateUpdate(deltaTime);
			Quad currentJumperQuad = jumper.positionToQuad();
			Quad futureJumperQuad = Jumper.calculateQuadAtPosition(newPosition);

			ArrayList<Point> minMovements = new ArrayList<Point>();
			for (Quad blockade : stickyQuads) {
				if (blockade.containsQuad(futureJumperQuad)) {

					double x = jumper.getXvelocity();
					double y = jumper.getYvelocity();
					Point newMinMovement = blockade.calculatePushingOtherToThis(currentJumperQuad, x, y);
					minMovements.add(newMinMovement);

				}
			}

			// if encountered platform...
			if (minMovements.size() > 0) {
				// stick to platfrom
				Point minimumMove = minMovements.stream().min(new PointComparator()).get();
				jumper.setVelocityToZero();
				jumper.moveBy(minimumMove.getX(), minimumMove.getY());
			} else {
				// move to new Position
				jumper.update(deltaTime);
			}
		} else {
			// if I ever add moving platforms!
		}

	}

	public double getPlayerXPos() {
		return jumper.getXposition();
	}

	public double getPlayerYPos() {
		return jumper.getYposition();
	}
	
	public double getPlayerWidth() {
		return Jumper.JUMPER_WIDTH;
	}
	
	public List<Quad> getStickyQuads() {
		return this.stickyQuads;
	}

}
