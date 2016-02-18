package model;

import java.util.ArrayList;
import java.util.List;

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
			StickyBlock futureJumperQuad = Jumper.calculateQuadAtPosition(newPosition);

			boolean encounteredBlock = false;
			for (StickyBlock blockade : stickyBlocks) {
				if (blockade.containsStickyBlock(futureJumperQuad)) {
					encounteredBlock = true;
					break;
				}
			}

			if (encounteredBlock) {
				minMovements.clear();
				double x = jumper.getXvelocity();
				double y = jumper.getYvelocity();
				StickyBlock currentJumperQuad = jumper.positionToQuad();
				for (StickyBlock blockade : stickyBlocks) {
					if (blockade.containsStickyBlock(futureJumperQuad)) {
						Point newMinMovement = blockade
								.calculatePushingOtherToThis(currentJumperQuad,
										x, y);
						minMovements.add(newMinMovement);
					}
				}

				// if encountered platform...
				// stick to platfrom
				Point lastMin = minMovements.get(0);
				for (Point p : minMovements) {
					if (lastMin.equals(Point.ZERO)) {
						lastMin = p;
					} else if (p.lessThan(lastMin) && !p.equals(Point.ZERO)) {
						lastMin = p;
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
