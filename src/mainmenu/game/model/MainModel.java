package mainmenu.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mainmenu.game.model.block.Block;
import mainmenu.game.model.block.BlockType;
import mainmenu.game.model.comparator.PairComparator;
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
	
	private boolean jumperReachedEnd;
	
	public MainModel(String levelname) {
		jumperReachedEnd = false;
		Level level = new Level(levelname);

		//System.out.println(level);
		this.jumper = new Jumper(level.getPlayerPosition());

		blocks = new ArrayList<Block>();
		for (Quad quad : level.getQuadData()) {
			blocks.add(Block.fromQuad(quad, BlockType.STICKY));
		}

		blocks.add(Block.fromQuad(level.getFinishQuad(), BlockType.FINISH));

	}

	private ArrayList<Pair<Block, Point>> minMovements = new ArrayList<>();

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

						Optional<Point> newMinMovement = blockade.calculatePushingQuadToThis(currentJumperQuad, x, y);
						if (newMinMovement.isPresent()) {
							minMovements.add(new Pair<>(blockade, newMinMovement.get()));
						}

					}

				}

				// if encountered platform...
				// find the most we can move before hitting something
				// (the min distance) that's not zero
				Optional<Pair<Block, Point>> minimum = minMovements.stream().min(new PairComparator());

				if (minimum.isPresent()) {
					// Now I can use the type of block here to react to each
					// type uniquely
					Pair<Block, Point> impact = minimum.get();

					if (impact.getItem1().getBLockType() == BlockType.STICKY) {
						Point min = impact.getItem2();
						jumper.setVelocityToZero();
						jumper.moveBy(min.getX(), min.getY());
						jumper.setStuck(true);
					}
					if (impact.getItem1().getBLockType() == BlockType.FINISH) {
						jumperReachedEnd = true;
					}
				}

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

	public List<Block> getBlockData() {
		return blocks;
	}

	public boolean jumperReachedEnd() {
		return jumperReachedEnd;
	}

	public void addJumpPowerToJumper(double angle, double power) {
		if (jumper.isStuck()) {
			jumper.setAngularVelocity(angle, power);
			jumper.setStuck(false);
		}
	}

}
