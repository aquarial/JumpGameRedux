package testing;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.GamePanel;
import view.JPanelConstructor;

public class MainTester {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new JFrame("Game Test Frame");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(800, 600);
				f.setResizable(false);
				f.setVisible(true);
				GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice device = env.getDefaultScreenDevice();
				device.setFullScreenWindow(f);
				f.validate();
				GamePanel gp = JPanelConstructor.makeNewGamePanel("001", f.getWidth(), f.getHeight());
				f.add(gp);
				gp.startlevel();
			}
		});
	}
}
