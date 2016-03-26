package mainmenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mainmenu.game.GamePanel;
import mainmenu.levelselect.SelectPanel;
import mainmenu.splashscreen.SplashPanel;

public class MainMenu {

	private JPanel contentPane;
	private SplashPanel splashpanel;
	private SelectPanel selectpanel;
	private GamePanel gamepanel;

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

		splashpanel = new SplashPanel();
		splashpanel.setOnRunFunction(waitSecondsThenGoToSelectPanel());
		selectpanel = new SelectPanel();
		selectpanel.addActionListenerToButton(goToGameStartLevel());
		gamepanel = new GamePanel();
		gamepanel.setOnFinish(goToSelectPanel());

		initState(MenuState.SPLASH_SCREEN);
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
			splashpanel.run();
			contentPane.add(splashpanel, BorderLayout.CENTER);
			break;
		case LEVEL_SELECT:
			contentPane.add(selectpanel, BorderLayout.CENTER);
			break;
		case PLAY_GAME:
			gamepanel.startlevel(selectpanel.getSelectedLevel());
			contentPane.add(gamepanel, BorderLayout.CENTER);
			break;
		default:
			break;
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	public Thread waitSecondsThenGoToSelectPanel() {
		return new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					int reps = 350;
					for (int i = 0; i < reps; i++) {
						Thread.sleep(60);
						splashpanel.increaseAlphaBy(1.0f / reps);
						splashpanel.repaint();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				initState(MenuState.LEVEL_SELECT);
			}
		};
	}

	public RunCode goToSelectPanel() {
		return new RunCode() {
			public void run() {
				initState(MenuState.LEVEL_SELECT);
			}
		};
	}

	ActionListener goToGameStartLevel() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initState(MenuState.PLAY_GAME);
			}
		};
	}

}
