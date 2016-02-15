package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.MainModel;
import model.Quad;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int xcenter = 400;
	private static int ycenter = 300;

	private MainModel model;

	public GamePanel(MainModel m) {
		super();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				double xdiff = e.getX() - xcenter;
				double ydiff = e.getY() - ycenter;

				double angle = Math.atan2(xdiff, ydiff);
				double power = calculatePowerFromDiffs(xdiff, ydiff);

				model.addJumpPowerToJumper(angle, power);
			}

			private double calculatePowerFromDiffs(double xdiff, double ydiff) {
				return 5;
			}

		});

		model = m;
	}

	public void startlevel() {
		Thread th = new MyThread(this, model);
		th.start();
	}

	public void renderGame() {
		repaint();
	}

	private void drawGraphics(Graphics g) {
		BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

		Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(Color.BLUE);
		drawPlayerAtCenter(g2);

		g2.setColor(Color.BLACK);
		for (Quad stickyQuad : model.getStickyQuads()) {
			drawStickyQuad(stickyQuad, g2);
		}

		g2.setColor(Color.GREEN);
		g2.drawOval(xcenter - 200, ycenter - 200, 400, 400);
		g2.drawOval(xcenter - 100, ycenter - 100, 200, 200);

		g.drawImage(bufferedImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		drawGraphics(g);
	}

	private void drawStickyQuad(Quad stickyQuad, Graphics2D g2) {
		int x1 = modelUnitToPixels(stickyQuad.getX1() - model.getPlayerXPos());
		int y1 = modelUnitToPixels(model.getPlayerYPos() - stickyQuad.getY2());
		int x2 = modelUnitToPixels(stickyQuad.getX2() - model.getPlayerXPos());
		int y2 = modelUnitToPixels(model.getPlayerYPos() - stickyQuad.getY1());

		g2.fillRect(xcenter + x1, ycenter + y1, x2 - x1, y2 - y1);
		// System.out.println("(" + model.getPlayerXPos() + ", " +
		// model.getPlayerYPos() + ")");
		// System.out.println("y = " + (y1 + ycenter));
		// g2.drawRect(300, 0, 30, 100);
	}

	private void drawPlayerAtCenter(Graphics2D g2) {
		int playerRad = modelUnitToPixels(model.getPlayerWidth() / 2);
		g2.fillRect(xcenter - playerRad, ycenter - playerRad, playerRad * 2, playerRad * 2);

	}

	private int modelUnitToPixels(double unit) {
		return (int) (unit * 25);
	}

	public void setWidth(int width) {
		xcenter = width / 2;
	}

	public void setHeight(int height) {
		ycenter = height / 2;
	}
}
