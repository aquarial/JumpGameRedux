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

	private ArrayList<Point> minMovements = new ArrayList<>();

	/**
	 * Updates the model based on time passed. Moves the Jumper/Player.
	 * 
	 * @param deltaTime
	 */
	public void updateModel(double deltaTime) {

		if (!jumper.isStuck()) {
			Point newPosition = jumper.calculateUpdate(deltaTime);
			Quad futureJumperQuad = Jumper.calculateQuadAtPosition(newPosition);

			boolean encounteredQuad = false;
			for (Quad blockade : stickyQuads) {
				if (blockade.containsQuad(futureJumperQuad)) {
					encounteredQuad = true;
					break;
				}
			}

			if (encounteredQuad) {
				minMovements.clear();
				for (Quad blockade : stickyQuads) {
					double x = jumper.getXvelocity();
					double y = jumper.getYvelocity();
					Quad currentJumperQuad = jumper.positionToQuad();
					Point newMinMovement = blockade.calculatePushingOtherToThis(currentJumperQuad, x, y);
					minMovements.add(newMinMovement);
				}

				// if encountered platform...
				// stick to platfrom
				Point minimumMove = minMovements.stream().min(new PointComparator()).get();
				jumper.setVelocityToZero();
				jumper.moveBy(minimumMove.getX(), minimumMove.getY());
				jumper.setStuck(true);
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

	public boolean jumperReachedEnd() {
		return false;
	}

	public void addJumpPowerToJumper(double angle, double power) {
		if (jumper.isStuck()) {
			jumper.setAngularVelocity(angle, power);
			jumper.setStuck(false);
		}
	}

}
