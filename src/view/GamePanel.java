package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.MainModel;
import model.Quad;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int xcenter = 300;
	private static final int ycenter = 200;

	private MainModel model;

	public GamePanel(MainModel m) {
		super();
		model = m;
	}

	public void paint(Graphics g) {
		System.out.println("print");
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLUE);
		drawPlayerAtCenter(g2);

		g2.setColor(Color.BLACK);
		for (Quad stickyQuad : model.getStickyQuads()) {
			drawStickyQuad(stickyQuad, g2);
		}

	}

	private void drawStickyQuad(Quad stickyQuad, Graphics2D g2) {
		System.out.println("draw quad at " + stickyQuad.getX2() + " , " + stickyQuad.getY2());
	}

	private void drawPlayerAtCenter(Graphics2D g2) {
		int playerRad = modelUnitToPixels(model.getPlayerWidth() / 2);
		g2.fillRect(xcenter - playerRad, ycenter - playerRad, playerRad, playerRad);

	}

	private int modelUnitToPixels(double unit) {
		return (int) (unit * 30);
	}
}
