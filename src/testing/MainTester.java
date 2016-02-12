package testing;

import model.Quad;

public class MainTester {

	public static void main(String[] args) {

		Quad q = new Quad(0, 0, 0, 0);

		for (double ang = -Math.PI; ang < Math.PI; ang += Math.PI / 8) {
			System.out.print(ang / Math.PI);
			q.removeOtherFromThis(null, ang);
		}

		// SwingUtilities.invokeLater(new Runnable() {
		// public void run() {
		// JFrame f = new JFrame("Game Test Frame");
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.setSize(600, 400);
		// f.setVisible(true);
		// f.add(JPanelConstructor.makeNewGamePanel());
		// }
		// });

	}
}
