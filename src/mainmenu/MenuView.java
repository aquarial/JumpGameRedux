package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import mainmenu.game.GamePanel;

class MenuView {

	private JComboBox<String> levelSelecter;
	private JPanel contentPane;

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
			System.out.println("splash screen state");
			initSplashScreen();
			break;
		case LEVEL_SELECT:
			System.out.println("level selecect state");
			initLevelSelect();
			break;
		case PLAY_GAME:
			System.out.println("play game state");
			initPlayGame();
			break;
		default:
			System.out.println("default state");
			break;
		}
		contentPane.revalidate();
		contentPane.repaint();
	}

	void initSplashScreen() {
		JPanel blue = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(0, 255, 255));
				g2.fillRect(0, 0, this.getWidth(), this.getHeight());
				g2.setColor(Color.BLACK);
				char[] text = "Welcome to game".toCharArray();
				g2.drawChars(text, 0, text.length, 200, 150);
			}
		};
		contentPane.add(blue, BorderLayout.CENTER);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				initState(MenuState.LEVEL_SELECT);
			}
		}.start();
	}

	void initLevelSelect() {
		//
		// *******LEFT HAND SIDE
		JPanel LevelSelectPanel = new JPanel();
		LevelSelectPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		LevelSelectPanel.setBackground(Color.BLUE);

		// 1st element - "Play Level" Button
		JButton startLevelButton = new JButton("Start Level");
		startLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initState(MenuState.PLAY_GAME);
			}
		});
		LevelSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		LevelSelectPanel.add(startLevelButton);

		// 2nd element - "Select level" combo box
		levelSelecter = new JComboBox<>();
		levelSelecter.addItem("001");
		LevelSelectPanel.add(levelSelecter);

		//
		// ********MIDDLE BOX
		JPanel levelInfoPanel = new JPanel();
		levelInfoPanel.setBackground(Color.GREEN);

		contentPane.add(LevelSelectPanel, BorderLayout.WEST);
		contentPane.add(levelInfoPanel, BorderLayout.CENTER);

	}

	void initPlayGame() {
		GamePanel backgroundPanel = JPanelConstructor.makeNewGamePanel("001");
		backgroundPanel.startlevel();
		contentPane.add(backgroundPanel, BorderLayout.CENTER);
	}
}
