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

	/**
	 * Construct a StickyBlock from an array instead of passing doubles
	 * 
	 * @param corners
	 *            Array with <b>LENGTH OF 4</b>
	 */
	StickyBlock(double[] corners) {
		super(corners[0], corners[1], corners[2], corners[3]);
	}

	/**
	 * Construct a StickyBlock by passing values
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	StickyBlock(double x1, double y1, double x2, double y2) {
		super(x1, y2, x2, y2);
	}

}