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
	private static int xcenter;
	private static int ycenter;

	private BufferedImage bufferedImage;
	private Graphics2D graphicsForBufferedImage;
    private MainModel model;

	RunCode endGame;

	public void setOnFinish(RunCode goToSelectPanel) {
		endGame = goToSelectPanel;
	}

	public GamePanel(int width, int height) {
		addClickListener();
		setBackground(Color.WHITE);

		xcenter = width / 2;
		ycenter = height / 2;
		bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		graphicsForBufferedImage = (Graphics2D) bufferedImage.getGraphics();
	}

	public void startlevel(String levelname) {
		model = new MainModel(levelname);
		Thread th = new GameThread(this, model);
		th.start();
	}

	/**
	 * Just calls repaint(). Clarity method.
	 */
	void renderGame() {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
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
				g2.setColor(Color.CYAN);
			}
			drawStickyQuad(stickyQuad, g2);
		}

		g.drawImage(bufferedImage, 0, 0, null);
	}

	/**
	 * Translates the model coordinates to screen coordinates, and fills in the
	 * pixel rectangle at that location
	 * 
	 * @param stickyQuad
	 * @param g2
	 */
	private void drawStickyQuad(Block stickyQuad, Graphics2D g2) {
		int x1 = modelUnitToPixels(stickyQuad.getX1() - model.getPlayerXPos());
		int y1 = modelUnitToPixels(model.getPlayerYPos() - stickyQuad.getY2());
		int x2 = modelUnitToPixels(stickyQuad.getX2() - model.getPlayerXPos());
		int y2 = modelUnitToPixels(model.getPlayerYPos() - stickyQuad.getY1());

		g2.fillRect(xcenter + x1, ycenter + y1, x2 - x1, y2 - y1);
	}

	/**
	 * 
	 * @param g2
	 */
	private void drawPlayerAtCenter(Graphics2D g2) {
		int playerRad = modelUnitToPixels(model.getPlayerWidth() / 2);
		g2.fillRect(xcenter - playerRad, ycenter - playerRad, playerRad * 2,
				playerRad * 2);

	}

	/**
	 * Scale from model to pixels
	 * 
	 * @param unit
	 * @return
	 */
	private int modelUnitToPixels(double unit) {
		return (int) (unit * 25);
	}

	/**
	 * 
	 * Adds mouseListener to <code>this</code>
	 * <p>
	 * Clears up constructor
	 * 
	 */
	private void addClickListener() {
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
				double normalPower = Math
						.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));
				return Math.min(normalPower / 18, 7.5);
			}

		});
	}

}
