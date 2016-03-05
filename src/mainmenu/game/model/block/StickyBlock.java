package mainmenu.game.model.block;

import mainmenu.game.model.level.Quad;

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
	 * Construct a Block from a Quad
	 * 
	 * @param q
	 * @return
	 */
	public static StickyBlock fromQuad(Quad q) {
		return new StickyBlock(q.getX1(), q.getY1(), q.getX2(), q.getY2());
	}

	StickyBlock(double[] corners) {
		super(corners[0], corners[1], corners[2], corners[3]);
	}

	StickyBlock(double x1, double y1, double x2, double y2) {
		super(x1, y2, x2, y2);
	}

}