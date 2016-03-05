package mainmenu;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class MainMenu {

	final static String N = SpringLayout.NORTH;
	final static String E = SpringLayout.EAST;
	final static String W = SpringLayout.WEST;
	final static String S = SpringLayout.SOUTH;
	private JComboBox<String> levelSelecter;

	public MainMenu() {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int width = 800;
				int height = 600;

				JFrame f = new JFrame("Demo");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setResizable(false);
				f.getContentPane().setLayout(null);

				//
				// *******LEFT HAND SIDE
				JPanel LevelSelectPanel = new JPanel();
				LevelSelectPanel.setBounds(5, 5, 145, 565);
				LevelSelectPanel.setBackground(Color.BLUE);
				f.getContentPane().add(LevelSelectPanel);
				DefaultListModel<String> dlm = new DefaultListModel<>();
				dlm.addElement("001");
				for (int i = 0; i < 100; i++) {
					dlm.addElement("" + i);
				}

				// 1st element - "Play Level" Button
				JButton startLevelButton = new JButton("Play Level");
				startLevelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Selected: " + levelSelecter.getSelectedItem());
					}
				});
				LevelSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				LevelSelectPanel.add(startLevelButton);

				// 2nd element - "Select level" combo box
				levelSelecter = new JComboBox<>();
				LevelSelectPanel.add(levelSelecter);

				//
				// ********MIDDLE BOX
				JPanel levelInfoPanel = new JPanel();
				levelInfoPanel.setBounds(155, 5, 635, 565);
				levelInfoPanel.setBackground(Color.GREEN);
				f.getContentPane().add(levelInfoPanel);
				levelInfoPanel.setLayout(null);

				// GamePanel backgroundPanel =
				// JPanelConstructor.makeNewGamePanel("001");
				// backgroundPanel.startlevel();
				// f.getContentPane().add(backgroundPanel, BorderLayout.CENTER);

				f.setVisible(true);
				f.validate();
			}
		});
	}
}
