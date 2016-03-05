package mainmenu.game.model.block;

public class FinishBlock extends Block {

	FinishBlock(double[] corners) {
		super(corners[0], corners[1], corners[2], corners[3]);
	}

	FinishBlock(double x1, double y1, double x2, double y2) {
		super(x1, y2, x2, y2);
	}

}
