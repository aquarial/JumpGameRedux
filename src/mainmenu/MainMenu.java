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

	private JPanel contentPanel;
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

		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		f.getContentPane().add(contentPanel, BorderLayout.CENTER);

		f.setVisible(true);
		f.validate();

		splashpanel = new SplashPanel(width, height);
		splashpanel.setOnRunFunction(waitSecondsThenGoToSelectPanel());

		selectpanel = new SelectPanel();
		selectpanel.addActionListenerToStartLevel(goToGameStartLevel());

		gamepanel = new GamePanel(width, height);
		gamepanel.setOnFinish(goToSelectPanel());

		initState(MenuState.SPLASH_SCREEN);
	}

	/**
	 * Resets the menu. Sets up parts ready for the state provided
	 * 
	 * @param newstate
	 */
	void initState(MenuState newstate) {
		contentPanel.removeAll();
		switch (newstate) {
		case SPLASH_SCREEN:
			contentPanel.add(splashpanel, BorderLayout.CENTER);
			splashpanel.run();
			break;
		case LEVEL_SELECT:
			contentPanel.add(selectpanel, BorderLayout.CENTER);
			break;
		case PLAY_GAME:
			gamepanel.startlevel(selectpanel.getSelectedLevel());
			contentPanel.add(gamepanel, BorderLayout.CENTER);
			break;
		default:
			break;
		}
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	public Thread waitSecondsThenGoToSelectPanel() {
		return new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					int reps = 200;
					for (int i = 0; i < reps; i++) {
						Thread.sleep(1500 / reps);
						splashpanel.increaseAlphaBy(1.0f / reps);
						splashpanel.repaint();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(500);
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
