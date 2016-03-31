package mainmenu.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import mainmenu.RunCode;
import mainmenu.game.model.MainModel;
import mainmenu.game.model.block.Block;
import mainmenu.game.model.block.BlockType;

/**
 * A panel that should be added to a parent component to show a game.
 * <p>
 * Runs one game through completion.
 * 
 * @author karl
 * 
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static int xcenter = 400;
	private static int ycenter = 300;

	private BufferedImage bufferedImage;
	private Graphics2D graphicsForBufferedImage;

	private MainModel model;

	RunCode endGame;

	/**
	 * Sets the closure to run when the player reaches the end
	 * 
	 * @param goToSelectPanel
	 */
	public void setOnFinish(RunCode goToSelectPanel) {
		endGame = goToSelectPanel;
	}

	/**
	 * Sets up instance variables
	 * 
	 * @param width
	 * @param height
	 */
	public GamePanel(int width, int height) {
		addClickToJumpListener();

		xcenter = width / 2;
		ycenter = height / 2;
		bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		graphicsForBufferedImage = (Graphics2D) bufferedImage.getGraphics();
	}

	/**
	 * Starts running/rendering a game in this gamepanel
	 * 
	 * @param levelname
	 */
	public void startlevel(String levelname) {
		model = new MainModel(levelname);
		new GameThread(this, model).start();
	}

	/**
	 * Never call paint directly
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = graphicsForBufferedImage;

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(Color.BLUE);
		this.drawPlayerAtCenter(g2);

		g2.setColor(new Color(235, 255, 235));
		g2.drawOval(xcenter - 200, ycenter - 200, 400, 400);
		g2.drawOval(xcenter - 100, ycenter - 100, 200, 200);

		for (Block stickyQuad : model.getBlockData()) {
			if (stickyQuad.getBLockType() == BlockType.STICKY) {
				g2.setColor(Color.BLACK);
			}
			if (stickyQuad.getBLockType() == BlockType.FINISH) {
				g2.setColor(new Color(0, 255, 255));
			}
			this.drawStickyQuad(stickyQuad, g2);
		}

		g.drawImage(bufferedImage, 0, 0, null);
	}

	private void drawStickyQuad(Block stickyQuad, Graphics2D g2) {
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

	private void addClickToJumpListener() {
		// Jump Listener
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				double xdiff = e.getX() - xcenter;
				double ydiff = e.getY() - ycenter;

				double angle = Math.atan2(ydiff, xdiff);
				double power = calculatePowerFromDiffs(xdiff, ydiff);

				model.addJumpPowerToJumper(angle, power);
			}

			private double calculatePowerFromDiffs(double xdiff, double ydiff) {
				double normalPower = Math.pow(Math.pow(xdiff, 2) + Math.pow(ydiff, 2), 0.5);
				return Math.min(normalPower / 18, 7.5);
			}

		});
	}

}
