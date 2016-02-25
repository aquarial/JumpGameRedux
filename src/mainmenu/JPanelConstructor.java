package mainmenu;

import game.GamePanel;
import game.io.Level;
import game.model.MainModel;

/**
 * Provides functions to create JPanels for the mainmenu
 *
 */
public class JPanelConstructor {

	/**
	 * Produces a JPanel (GamePanel) that will render one game through
	 * completion.
	 * <p>
	 * Receiver will need to call pan.startlevel();
	 * 
	 * @param levelname
	 *            Name of level xml file
	 * 
	 * @return
	 */
	public static GamePanel makeNewGamePanel(String levelname) {
		Level level = new Level(levelname);
		MainModel model = new MainModel(level);

		GamePanel pan = new GamePanel(model);
		return pan;
	}

}
