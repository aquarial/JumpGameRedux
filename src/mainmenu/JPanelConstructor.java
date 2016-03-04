package mainmenu;

import io.Level;
import mainmenu.game.GamePanel;

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
		GamePanel pan = new GamePanel(level);
		return pan;
	}

}
