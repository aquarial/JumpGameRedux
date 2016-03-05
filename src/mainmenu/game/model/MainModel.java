package mainmenu.game.model;

import java.util.ArrayList;
import java.util.List;

import mainmenu.game.model.block.Block;
import mainmenu.game.model.block.BlockType;
import mainmenu.game.model.level.Level;
import mainmenu.game.model.level.Point;
import mainmenu.game.model.level.Quad;

/**
 * 
 * @author karl
 * 
 */
public class MainModel {

	private Jumper jumper;
	private List<Block> blocks;

	public MainModel(String levelname) {
		Level level = new Level(levelname);

		this.jumper = new Jumper(level.getPlayerPosition());

		blocks = new ArrayList<Block>();
		for (Quad quad : level.getQuadData()) {
			blocks.add(Block.fromQuad(quad, BlockType.STICKY));
		}

		blocks.add(Block.fromQuad(level.getFinishQuad(), BlockType.FINISH));

	}

	public MainModel(Point playerLocation, List<Block> obsticales) {
		this.jumper = new Jumper(playerLocation);
		this.blocks = obsticales;
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
			for (Block blockade : blocks) {
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

				for (Block blockade : blocks) {
					if (blockade.containsCornerArray(futureJumperCorners)) {
						Point newMinMovement = blockade.calculatePushingRectangleToThis(currentJumperQuad, x, y);
						minMovements.add(newMinMovement);
					}

				}

				// if encountered platform...
				// find the most we can move before hitting something
				// (the min distance) that's not zero
				Point minimum = minMovements.get(0);
				for (Point potentialMin : minMovements) {
					if (minimum.equals(Point.ZERO)) {
						minimum = potentialMin;
					} else if (potentialMin.lessThan(minimum) && !potentialMin.equals(Point.ZERO)) {
						minimum = potentialMin;
					}
				}
				jumper.setVelocityToZero();
				jumper.moveBy(minimum.getX(), minimum.getY());
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

	public List<? extends Quad> getBlockData() {
		return blocks;
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
