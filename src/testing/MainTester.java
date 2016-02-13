package testing;

import model.Quad;

public class MainTester {

	public static void main(String[] args) {

		Quad q1 = new Quad(4, 4, 9, 9);
		Quad q2 = new Quad(10, 6, 11, 7);

		q1.removeJumperFromThis(q2, 1, -1);
		//
		// for (double ang = -Math.PI; ang < Math.PI; ang += Math.PI / 8) {
		// System.out.print(ang / Math.PI + " ");
		// q1.removeJumperFromThis(q2, 1, 0);
		// }

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
