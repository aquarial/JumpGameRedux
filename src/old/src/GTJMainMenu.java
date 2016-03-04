package old.src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class GTJMainMenu extends JFrame {
	private JPanel levelSelectorPanel;
	private JLabel MainMenuBackgroundImage;
	private JButton PLAY;
	private GameJPanel GamePanel;
	private JTextPane showTime;
	private JPanel mainMenuPanel;
	private ArrayList<JButton> listOfButtons;
	private JPanel moreOptionsPanel;
	private JCheckBox DefaultScrolling;
	private JPanel contentPane;
	private JButton quitButton;
	private JButton moreOptions;
	private static JTextField optionsName;
	private JButton loadButton;

	private static GTJMainMenu frame;
	public static String saveFileName;
	private JButton saveButton;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GTJMainMenu();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false);
					frame.setTitle("JUMP! - Karl Stephan");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public GTJMainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(785, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listOfButtons = new ArrayList<JButton>();

		listOfButtons.add(new levelSelectingJButton(1, new Rectangle(20, 11,
				89, 38)));

		listOfButtons.add(new levelSelectingJButton(2, new Rectangle(129, 11,
				89, 38)));
		listOfButtons.add(new levelSelectingJButton(3, new Rectangle(238, 11,
				89, 38)));
		listOfButtons.add(new levelSelectingJButton(4, new Rectangle(347, 11,
				89, 38)));
		listOfButtons.add(new levelSelectingJButton(5, new Rectangle(456, 11,
				89, 38)));
		listOfButtons.add(new levelSelectingJButton(6, new Rectangle(565, 11,
				89, 38)));
		listOfButtons.add(new levelSelectingJButton(7, new Rectangle(674, 11,
				89, 38)));

		GamePanel = new GameJPanel();
		GamePanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				GamePanel.player.tryToJumpAtMouse(e, new Point(frame.getX(),
						frame.getY()));
			}

		});
		GamePanel.setVisible(false);

		mainMenuPanel = new JPanel();
		mainMenuPanel.setBounds(0, 0, 770, 545);
		contentPane.add(mainMenuPanel);
		mainMenuPanel.setLayout(null);

		levelSelectorPanel = new JPanel();
		levelSelectorPanel.setBounds(0, 0, 784, 60);
		mainMenuPanel.add(levelSelectorPanel);
		levelSelectorPanel.setBackground(Color.DARK_GRAY);
		levelSelectorPanel.setVisible(false);
		levelSelectorPanel.setLayout(null);

		levelSelectorPanel.add(listOfButtons.get(0));
		levelSelectorPanel.add(listOfButtons.get(1));
		levelSelectorPanel.add(listOfButtons.get(2));
		levelSelectorPanel.add(listOfButtons.get(3));
		levelSelectorPanel.add(listOfButtons.get(4));
		levelSelectorPanel.add(listOfButtons.get(5));
		levelSelectorPanel.add(listOfButtons.get(6));

		moreOptionsPanel = new JPanel();
		moreOptionsPanel.setVisible(false);
		moreOptionsPanel.setBackground(Color.DARK_GRAY);
		moreOptionsPanel.setBounds(0, 485, 784, 60);
		mainMenuPanel.add(moreOptionsPanel);

		DefaultScrolling = new JCheckBox("Scrolling always on");
		DefaultScrolling.setToolTipText("");

		loadButton = new JButton("Load Save");
		loadButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
				try {
					saveFileName = optionsName.getText();
					FileInputStream fs = new FileInputStream(saveFileName
							+ ".ser");
					ObjectInputStream os = new ObjectInputStream(fs);
					Level.levelTimes = (ArrayList<Double>) os.readObject();
					Level.levelClicks = (ArrayList<Integer>) os.readObject();
					try {
						Level.levelClicks.get(0);
					} catch (Exception fe) {
						for (int i = 0; i < Level.levelTimes.size(); i++) {
							Level.levelClicks.add(99);
						}
					}
					os.close();
					fs.close();
					levelSelectorPanel.setVisible(false);
					playClicked();
				} catch (IOException | ClassNotFoundException r) {
				}
			}
		});

		JLabel lblSaveFileName = new JLabel("Save file name:");
		lblSaveFileName.setForeground(Color.WHITE);
		lblSaveFileName.setBackground(Color.WHITE);
		optionsName = new JTextField();
		optionsName.setText("JumpLevelTimes");
		optionsName.setColumns(10);
		moreOptionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		moreOptionsPanel.add(DefaultScrolling);

		lblNewLabel = new JLabel("                    ");
		moreOptionsPanel.add(lblNewLabel);
		moreOptionsPanel.add(lblSaveFileName);

		JLabel label_1 = new JLabel("  ");
		moreOptionsPanel.add(label_1);
		moreOptionsPanel.add(optionsName);

		saveButton = new JButton("Save");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FileOutputStream fis;
				try {
					saveFileName = optionsName.getText();
					fis = new FileOutputStream(saveFileName + ".ser");

					ObjectOutputStream os = new ObjectOutputStream(fis);
					os.writeObject(Level.levelTimes);
					os.writeObject(Level.levelClicks);
					os.close();
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		label = new JLabel("   ");
		moreOptionsPanel.add(label);
		moreOptionsPanel.add(saveButton);

		lblNewLabel_1 = new JLabel("  ");
		moreOptionsPanel.add(lblNewLabel_1);
		moreOptionsPanel.add(loadButton);

		PLAY = new JButton("Play");
		PLAY.setBounds(342, 300, 100, 40);
		mainMenuPanel.add(PLAY);
		PLAY.setForeground(Color.BLACK);
		PLAY.setFont(new Font("Tahoma", Font.BOLD, 11));
		PLAY.setBackground(Color.WHITE);
		PLAY.setMnemonic('p');
		PLAY.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playClicked();
			}
		});

		PLAY.setToolTipText("Select Level");
		PLAY.requestFocus();

		moreOptions = new JButton("Options");
		moreOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moreOptionsPanel.setVisible(!moreOptionsPanel.isVisible());
			}
		});
		moreOptions.setFont(new Font("Tahoma", Font.BOLD, 11));
		moreOptions.setBackground(Color.WHITE);
		moreOptions.setForeground(Color.BLACK);
		moreOptions.setToolTipText("Show options");
		moreOptions.setBounds(342, 350, 100, 30);
		mainMenuPanel.add(moreOptions);

		showTime = new JTextPane();
		showTime.setBounds(212, 200, 360, 70);
		mainMenuPanel.add(showTime);
		showTime.setVisible(false);
		showTime.setEditable(false);

		MainMenuBackgroundImage = new JLabel();
		MainMenuBackgroundImage.setBounds(0, 0, 784, 563);
		mainMenuPanel.add(MainMenuBackgroundImage);
		MainMenuBackgroundImage.setIcon(new ImageIcon(GTJMainMenu.class
				.getResource("/gameTimeJump/MainMenuBackgroundImage.jpg")));

		MainMenuBackgroundImage.requestFocus();
		GamePanel.setBackground(Color.MAGENTA);
		GamePanel.setBounds(0, 0, 796, 563);
		GamePanel.setDoubleBuffered(true);
		contentPane.add(GamePanel);
		GamePanel.setLayout(null);

		quitButton = new JButton("I Give Up");
		quitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GamePanel.throwTheException(-1 + "");
			}
		});
		quitButton.setBackground(Color.RED);
		quitButton.setBounds(0, 529, 89, 23);
		GamePanel.add(quitButton);
		GamePanel.setUpOneGame(0);
	}

	public void letTheGameBegin(int levelNum) {
		mainMenuPanel.setVisible(false);
		GamePanel.setUpOneGame(levelNum);
		if (DefaultScrolling.isSelected()) {
			GamePanel.currentLevel.scrolling = true;
		}
		moreOptionsPanel.setVisible(false);
		GamePanel.setVisible(true);

		new RunOneGame().start();
	}

	private class RunOneGame extends Thread {
		public void run() {
			long timeOfStart = System.currentTimeMillis();
			try {
				while (true) {
					double time = ((System.currentTimeMillis() - timeOfStart) / 1000F);
					frame.setTitle(Level.jumpCount + " clicks in "
							+ ((int) (time * 1000)) / 1000F + "sc");
					GamePanel.player.passOneTurn();
					GamePanel.player.trySticking();
					GamePanel.checkIfWin();
					frame.repaint();
					try {
						// 30
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (IllegalStateException e) {
				if (!e.getMessage().equalsIgnoreCase("-1")) {

					double timeTaken = ((System.currentTimeMillis() - timeOfStart) / 1000F);
					int levelNumber = Integer.parseInt(e.getMessage());
					mainMenuPanel.setVisible(true);
					levelSelectorPanel.setVisible(false);
					frame.setTitle("Jump");
					showTime.setVisible(true);
					if (timeTaken > 2) {
						if (levelNumber > Level.levelTimes.size()) {
							Level.levelTimes.add(timeTaken);
							Level.levelClicks.add(Level.jumpCount);
						} else if (timeTaken < Level.levelTimes
								.get(levelNumber - 1)) {
							Level.levelTimes.set(levelNumber - 1, timeTaken);
						}
						showTime.setText("After jumping " + Level.jumpCount
								+ " times, you completed the level in "
								+ ((int) (timeTaken * 1000)) / 1000F
								+ " seconds.");
					} else {
						showTime.setText("Level "
								+ levelNumber
								+ " doesn't exist yet. Sorry for the inconvenience.\n"
								+ "In the meantime you can see if you can beat your earlier times.\n"
								+ "Hover the mouse over a level button to see your best time.");
					}

					if (Level.levelClicks.get(levelNumber - 1) > Level.jumpCount) {
						Level.levelClicks.set(levelNumber - 1, Level.jumpCount);

					}

					Level.jumpCount = 0;
					MainMenuBackgroundImage.setVisible(true);
					PLAY.setVisible(true);
					PLAY.requestFocus();

					GamePanel.setVisible(false);
				}
			} catch (ArithmeticException e) {
				double timeTaken = ((System.currentTimeMillis() - timeOfStart) / 1000F);
				GamePanel.setVisible(false);
				mainMenuPanel.setVisible(true);
				showTime.setVisible(true);
				showTime.setText("You played " + ((int) (timeTaken * 1000))
						/ 1000F + " seconds of the level before giving up."
						+ "\nIn that time, you jumped " + Level.jumpCount
						+ " times.");
				MainMenuBackgroundImage.setVisible(true);
				Level.jumpCount = 0;
				PLAY.setVisible(true);
				PLAY.requestFocus();
			}

			frame.setTitle("JUMP! - Karl Stephan");
		}
	}

	private void playClicked() {
		int sizeOfArray = Level.levelTimes.size();
		// (Fg 2)
		for (int i = 0; i < listOfButtons.size(); i++) {
			JButton levelSelector = listOfButtons.get(i);
			if (sizeOfArray >= i + 1) {
				levelSelector
						.setToolTipText((double) Math.round((Level.levelTimes
								.get(i)) * 100)
								/ 100
								+ " seconds, "
								+ (Level.levelClicks.get(i)) + " clicks");
			}
			levelSelector.setVisible(Level.levelTimes.size() >= i);
		}

		levelSelectorPanel.setVisible(!levelSelectorPanel.isVisible());
		levelSelectorPanel.requestFocus();

	}

	private class levelSelectingJButton extends JButton {

		levelSelectingJButton(int number, Rectangle bounds) {
			setText("Level " + number);
			setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
			final int num = number;

			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					letTheGameBegin(num);
				}
			});
		}
	}
}
