package mainmenu.game.model.block;

/**
 * Represents a quadrilateral
 * <p>
 * Models an obsticle in the game
 * 
 * @author karl
 * 
 */
public class StickyBlock extends Block {

	StickyBlock(double[] corners) {
		super(corners[0], corners[1], corners[2], corners[3]);
	}

	StickyBlock(double x1, double y1, double x2, double y2) {
		super(x1, y2, x2, y2);
	}

}