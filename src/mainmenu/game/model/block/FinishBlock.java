package mainmenu.game.model.block;

import mainmenu.game.model.level.Quad;

public class FinishBlock extends Block {

	/**
	 * Construct a FinishBlock from a Quad
	 * 
	 * @param q
	 * @return
	 */
	public static FinishBlock fromQuad(Quad q) {
		return new FinishBlock(q.getX1(), q.getY1(), q.getX2(), q.getY2());
	}

	FinishBlock(double[] corners) {
		super(corners[0], corners[1], corners[2], corners[3]);
	}

	FinishBlock(double x1, double y1, double x2, double y2) {
		super(x1, y2, x2, y2);
	}

}
