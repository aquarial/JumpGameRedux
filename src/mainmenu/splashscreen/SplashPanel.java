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

	public void run() {
		splashimage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		g2splashimage = (Graphics2D) splashimage.getGraphics();
		runOnStart.start();
	}

	@Override
	public void paint(Graphics g) {

		g2splashimage.setColor(Color.CYAN);
		g2splashimage.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2splashimage.setColor(new Color(0, 0, 0, alphavalue));
		g2splashimage.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.drawImage(splashimage, 0, 0, null);
	}

	public void setOnRunFunction(Thread runCode) {
		runOnStart = runCode;
	}

	public void increaseAlphaBy(float delta_alpha) {
		alphavalue += delta_alpha;
	}
}
