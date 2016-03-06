package mainmenu.splashscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mainmenu.MainMenu;

public class SplashPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Thread runOnStart;

	public SplashPanel(MainMenu mm) {
		runOnStart = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mm.goToSelectPanel();
			}
		};
	}

	public void start() {
		runOnStart.start();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 255, 255));
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2.setColor(Color.BLACK);
		char[] text = "Welcome to game".toCharArray();
		g2.drawChars(text, 0, text.length, 200, 150);
	}

}
