package mainmenu.splashscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SplashPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Thread runOnStart;
	private float alphavalue = 0;
	private BufferedImage splashimage;
	private Graphics2D g2splashimage;
	private int width;
	private int height;

	public SplashPanel(int width, int height) {
		this.width = width;
		this.height = height;

		splashimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2splashimage = (Graphics2D) splashimage.getGraphics();
	}

	public void run() {
		runOnStart.start();
	}

	@Override
	public void paint(Graphics g) {

		g2splashimage.setColor(Color.CYAN);
		g2splashimage.fillRect(0, 0, width, height);

		g2splashimage.setColor(new Color(0, 0, 0, alphavalue));
		g2splashimage.fillRect(0, 0, width, height);

		g.drawImage(splashimage, 0, 0, null);
	}

	public void setOnRunFunction(Thread runCode) {
		runOnStart = runCode;
	}

	public void increaseAlphaBy(float delta_alpha) {
		alphavalue += delta_alpha;
	}
}
