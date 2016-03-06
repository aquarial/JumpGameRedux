package testing;

import javax.swing.JFrame;

import mainmenu.JPanelConstructor;
import mainmenu.MainMenu;
import mainmenu.game.GamePanel;

public class MainTester {

	public static void main(String[] args) {
		// new MainMenu();
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
		GamePanel gp = JPanelConstructor.makeNewGamePanel("001");
		gp.startlevel();
		f.getContentPane().add(gp);
		f.revalidate();
		f.repaint();
	}
}
