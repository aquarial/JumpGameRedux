package view;

import javax.swing.JPanel;

import model.Level;
import model.MainModel;

public class JPanelConstructor {
	public static JPanel makeNewGamePanel(String levelname) {

		Level level = new Level(levelname);
		MainModel m = new MainModel(level);

		JPanel pan = new GamePanel();
		return pan;
	}
}
