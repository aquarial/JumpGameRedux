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

	class MyThread extends Thread {

		private GamePanel panel;

		public MyThread(GamePanel gp) {
			panel = gp;
		}

		@Override
		public void run() {
			long oldTime = System.currentTimeMillis();
			long newTime;
			long deltaTime;
			while (!model.jumperReachedEnd()) {
				newTime = System.currentTimeMillis();
				deltaTime = newTime - oldTime;
				model.updateModel(deltaTime / 1000.0);
				panel.repaint();

				if (deltaTime < 50) {
					safesleep(50 - deltaTime);
				}

				oldTime = newTime;
			}
		}
	}

	public void startlevel() {
		Thread th = new MyThread(this);
		th.start();
	}

	private void safesleep(long mili) {
		try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2.setColor(Color.BLUE);
		drawPlayerAtCenter(g2);

		g2.setColor(Color.BLACK);
		for (Quad stickyQuad : model.getStickyQuads()) {
			drawStickyQuad(stickyQuad, g2);
		}

	}

	private void drawStickyQuad(Quad stickyQuad, Graphics2D g2) {
		int x1 = modelUnitToPixels(stickyQuad.getX1() - model.getPlayerXPos());
		int y1 = modelUnitToPixels(model.getPlayerYPos() - stickyQuad.getY2());
		int x2 = modelUnitToPixels(stickyQuad.getX2() - model.getPlayerXPos());
		int y2 = modelUnitToPixels(model.getPlayerYPos() - stickyQuad.getY1());

		g2.fillRect(xcenter + x1, ycenter + y1, x2 - x1, y2 - y1);
		// System.out.println("(" + model.getPlayerXPos() + ", " +
		// model.getPlayerYPos() + ")");
		System.out.println("y = " + (y1 + ycenter));
		g2.drawRect(300, 0, 30, 100);
	}

	private void drawPlayerAtCenter(Graphics2D g2) {
		int playerRad = modelUnitToPixels(model.getPlayerWidth() / 2);
		g2.fillRect(xcenter - playerRad, ycenter - playerRad, playerRad * 2, playerRad * 2);

	}

	private int modelUnitToPixels(double unit) {
		return (int) (unit * 30);
	}
}
