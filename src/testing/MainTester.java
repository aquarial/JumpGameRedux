package testing;

import game.GamePanel;
import game.JPanelConstructor;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainTester {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int width = 800;
				int height = 600;

				JFrame f = new JFrame("Demo");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setResizable(false);
				f.setVisible(true);
				f.validate();
				GamePanel gp = JPanelConstructor.makeNewGamePanel("001", width,
						height);
				f.add(gp);
				gp.startlevel();
			}
		});
	}
}
