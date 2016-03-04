package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mainmenu.game.GamePanel;

public class MainMenu {

	public MainMenu() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int width = 800;
				int height = 600;

				JFrame f = new JFrame("Demo");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setResizable(false);

				GamePanel backgroundPanel = JPanelConstructor
						.makeNewGamePanel("001");
				backgroundPanel.startlevel();
				f.getContentPane().add(backgroundPanel, BorderLayout.CENTER);
				backgroundPanel.setBackground(Color.BLUE);
				backgroundPanel.setLayout(new BorderLayout(0, 0));
				f.setVisible(true);
				f.validate();
			}
		});
	}
}
