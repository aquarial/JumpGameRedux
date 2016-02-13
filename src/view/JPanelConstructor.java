package view;

import javax.swing.JPanel;

import model.Level;
import model.MainModel;

public class JPanelConstructor {
	public static GamePanel makeNewGamePanel(String levelname) {

		Level level = new Level(levelname);
		MainModel m = new MainModel(level);

		GamePanel pan = new GamePanel(m);
		return pan;
	}
}
