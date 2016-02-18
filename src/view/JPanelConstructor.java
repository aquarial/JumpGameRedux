package view;

import model.Level;
import model.MainModel;

public class JPanelConstructor {
	public static GamePanel makeNewGamePanel(String levelname, int width, int height) {

		Level level = new Level(levelname);
		MainModel m = new MainModel(level);

		GamePanel pan = new GamePanel(m, width, height);
		return pan;
	}
}
