package mainmenu;

import mainmenu.game.GamePanel;
import mainmenu.levelselect.SelectPanel;

/**
 * Provides functions to create JPanels for the mainmenu
 *
 */
class JPanelManager {

	private static GamePanel gamepanel = new GamePanel();
	private static SelectPanel selectpanel = new SelectPanel();

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
	public static GamePanel getAGamePanel(String levelname) {
		gamepanel.startlevel(levelname);
		return gamepanel;
	}

	public static SelectPanel getASelectPanel() {
		return selectpanel;
	}

}
