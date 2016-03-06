package mainmenu;

import javax.swing.SwingUtilities;

public class MainMenu {

	private MenuView view;
	private MenuState state;

	public MainMenu() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				state = MenuState.LEVEL_SELECT;
				view = new MenuView();

				view.initState(state);

			}
		});
	}

}
