package mainmenu.splashscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SplashPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Thread runOnStart;
	private float alphavalue;
	private BufferedImage splashimage;
	private Graphics2D g2splashimage;
	private int width;
	private int height;

	public SplashPanel(int width, int height) {
		this.width = width;
		this.height = height;

		alphavalue = 1;

		splashimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2splashimage = (Graphics2D) splashimage.getGraphics();
		g2splashimage.setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
	}

	public void waitThenFade() {
		runOnStart.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// I wanted to fade the splash image to become transparent. g.drawImage
		// doesn't allow me to specify an alpha to draw the image at, so I need
		// to clear the splashimage, redraw it with a different alpha, and draw
		// that on this.

		// clearRect resets the image to its background, and here the background
		// is transparent.
		g2splashimage.clearRect(0, 0, width, height);

		g2splashimage.setColor(new Color(0, .5f, .5f, alphavalue));
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
