package view;

import java.awt.Color;

import javax.swing.JPanel;

public class JPanelConstructor {
	public static JPanel makeNewGamePanel(String levelname) {

		Level level = new Level(levelname);
		// MainModel m = new MainModel(level);

		JPanel pan = new JPanel();
		pan.setBackground(Color.BLUE);
		return pan;
	}
}
