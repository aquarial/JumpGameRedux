package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		f.getContentPane().add(contentPane, BorderLayout.CENTER);

		// GamePanel backgroundPanel =
		// JPanelConstructor.makeNewGamePanel("001");
		// backgroundPanel.startlevel();
		// f.getContentPane().add(backgroundPanel, BorderLayout.CENTER);

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
			break;
		default:
			System.out.println("default state");
			break;
		}
	}

	void initLevelSelect() {
		//
		// *******LEFT HAND SIDE
		JPanel LevelSelectPanel = new JPanel();
		LevelSelectPanel.setBounds(5, 5, 145, 565);
		LevelSelectPanel.setBackground(Color.BLUE);
		contentPane.add(LevelSelectPanel);
		DefaultListModel<String> dlm = new DefaultListModel<>();
		dlm.addElement("001");
		for (int i = 0; i < 100; i++) {
			dlm.addElement("" + i);
		}

		// 1st element - "Play Level" Button
		JButton startLevelButton = new JButton("Start Level");
		startLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected: " + levelSelecter.getSelectedItem());
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
		levelInfoPanel.setBounds(155, 5, 635, 565);
		levelInfoPanel.setBackground(Color.GREEN);
		contentPane.add(levelInfoPanel);
		levelInfoPanel.setLayout(null);

	}
}
