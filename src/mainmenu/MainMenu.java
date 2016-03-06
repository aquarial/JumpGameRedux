package mainmenu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mainmenu.game.GamePanel;
import mainmenu.levelselect.SelectPanel;
import mainmenu.splashscreen.SplashPanel;

public class MainMenu {

	private JPanel contentPane;
	private SplashPanel splashpanel = new SplashPanel();
	private SelectPanel selectpanel = new SelectPanel();
	private GamePanel gamepanel = new GamePanel();

	public MainMenu() {
		int width = 800;
		int height = 600;

		JFrame f = new JFrame("Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height);
		f.setResizable(false);
		contentPane = (JPanel) f.getContentPane();
		contentPane.setLayout(new BorderLayout());

		f.setVisible(true);
		f.validate();
		
		
		initState(MenuState.LEVEL_SELECT);
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
			contentPane.add(splashpanel, BorderLayout.CENTER);
			break;
		case LEVEL_SELECT:
			contentPane.add(selectpanel, BorderLayout.CENTER);
			break;
		case PLAY_GAME:
			gamepanel.startlevel("001");
			contentPane.add(gamepanel, BorderLayout.CENTER);
			break;
		default:
			break;
		}
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	
	
	

}
