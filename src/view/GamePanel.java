package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.MainModel;
import model.Point;
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
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLUE);
		drawPlayerAtCenter(g2);

		g2.setColor(Color.BLACK);
		for (Quad stickyQuad : model.getStickyQuads()) {
			drawStickyQuad(stickyQuad, g2);
		}

	}

	private void drawStickyQuad(Quad stickyQuad, Graphics2D g2) {
		double x1 = (stickyQuad.getX1() - model.getPlayerXPos());
		double y1 = (model.getPlayerYPos() - stickyQuad.getY2());
		double x2 = (stickyQuad.getX2() - model.getPlayerXPos());
		double y2 = (model.getPlayerYPos() - stickyQuad.getY1());
		System.out.println("draw quad at " + x1 + " " + y1 + " " + x2 + " " + y2);
		// g2.drawRect(x1, y1, x2 - x1, y2 - y1);
	}

	public void pointRelativeToPlayerPos(double quadX, double quadY) {
		int newx = modelUnitToPixels(quadX - model.getPlayerXPos());
		int newy = modelUnitToPixels(model.getPlayerYPos() - quadY);

	}

	private void drawPlayerAtCenter(Graphics2D g2) {
		int playerRad = modelUnitToPixels(model.getPlayerWidth() / 2);
		g2.fillRect(xcenter - playerRad, ycenter - playerRad, playerRad, playerRad);

	}

	private int modelUnitToPixels(double unit) {
		return (int) (unit * 30);
	}
}
