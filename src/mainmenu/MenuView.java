package mainmenu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mainmenu.game.GamePanel;
import mainmenu.levelselect.SelectPanel;
import mainmenu.splashscreen.SplashPanel;

class MenuView {

	private JPanel contentPane;
	private SplashPanel splashpanel = new SplashPanel();
	private SelectPanel selectpanel = new SelectPanel();
	private GamePanel gamepanel = new GamePanel();

	public MenuView() {
		int width = 800;
		int height = 600;

		JFrame f = new JFrame("Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height);
		f.setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		f.getContentPane().add(contentPane, BorderLayout.CENTER);

		f.setVisible(true);
		f.validate();
	}

	/**
	 * Resets the menu. Sets up parts ready for the state provided
	 * 
	 * @param newstate
	 */
	void initState(MenuState newstate) {
		contentPane.removeAll();
		switch (newstate) {
		case SPLASH_SCREEN:
			initSplashScreen();
			break;
		case LEVEL_SELECT:
			initLevelSelect();
			break;
		case PLAY_GAME:
			initPlayGame();
			break;
		default:
			break;
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	void initSplashScreen() {
		contentPane.add(splashpanel, BorderLayout.CENTER);
	}

	void initLevelSelect() {
		contentPane.add(selectpanel, BorderLayout.CENTER);
	}

	void initPlayGame() {
		gamepanel.startlevel("001");
		contentPane.add(gamepanel, BorderLayout.CENTER);
	}
}
