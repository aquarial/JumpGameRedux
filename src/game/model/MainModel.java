package game.model;

import io.Level;

import java.util.ArrayList;
import java.util.List;

import util.Point;

/**
 * 
 * @author karl
 *
 */
public class MainModel {

	private List<StickyBlock> stickyBlocks;
	private Jumper jumper;

	public MainModel(Level level) {
		this(level.getPlayerPosition(), level.getStickyBlocks());
	}

	public MainModel(Point playerLocation, List<StickyBlock> obsticales) {
		this.jumper = new Jumper(playerLocation);
		this.stickyBlocks = obsticales;
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
			double[] futureJumperCorners = Jumper.calculateCornersAtPosition(newPosition);

			boolean encounteredBlock = false;
			for (StickyBlock blockade : stickyBlocks) {
				if (blockade.containsCornerArray(futureJumperCorners)) {
					encounteredBlock = true;
					break;
				}
			}

			if (encounteredBlock) {
				minMovements.clear();
				double x = jumper.getXvelocity();
				double y = jumper.getYvelocity();
				double[] currentJumperQuad = jumper.getCurrentCorners();

				for (StickyBlock blockade : stickyBlocks) {
					if (blockade.containsCornerArray(futureJumperCorners)) {
						Point newMinMovement = blockade.calculatePushingRectangleToThis(currentJumperQuad, x, y);
						minMovements.add(newMinMovement);
					}

				}

				// if encountered platform...
				// stick to platfrom
				Point lastMin = minMovements.get(0);
				for (Point potentialMin : minMovements) {
					if (lastMin.equals(Point.ZERO)) {
						lastMin = potentialMin;
					} else if (potentialMin.lessThan(lastMin) && !potentialMin.equals(Point.ZERO)) {
						lastMin = potentialMin;
					}
				}
				jumper.setVelocityToZero();
				jumper.moveBy(lastMin.getX(), lastMin.getY());
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

	public List<StickyBlock> getStickyBlocks() {
		return this.stickyBlocks;
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
