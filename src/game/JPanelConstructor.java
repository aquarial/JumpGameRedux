package game;

import io.Level;
import game.model.MainModel;

public class JPanelConstructor {
	public static GamePanel makeNewGamePanel(String levelname, int width, int height) {

		Level level = new Level(levelname);
		MainModel model = new MainModel(level);

		GamePanel pan = new GamePanel(model, width, height);
		return pan;
	}
}
