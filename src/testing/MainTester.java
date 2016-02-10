package testing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainTester {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame f = new JFrame("Game Test Frame");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(600, 400);
				f.setVisible(true);
			}
		});
	}

}
