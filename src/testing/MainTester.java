package testing;

import model.Quad;

public class MainTester {

	public static void main(String[] args) {

		Quad q1 = new Quad(0, 2, 2, 0);
		Quad q2 = new Quad(-1, 1, 0.5, 0);

		for (double ang = -Math.PI; ang < Math.PI; ang += Math.PI / 8) {
			System.out.print(ang / Math.PI + " ");
			q1.removeOtherFromThis(q2, ang);
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
