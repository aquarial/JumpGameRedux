package mainmenu;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class MainMenu {
	private JPanel LevelSelectPanel;

	public MainMenu() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				int width = 800;
				int height = 600;

				JFrame f = new JFrame("Demo");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setResizable(false);
				SpringLayout springLayout = new SpringLayout();
				f.getContentPane().setLayout(springLayout);

				// GamePanel backgroundPanel =
				// JPanelConstructor.makeNewGamePanel("001");
				// backgroundPanel.startlevel();
				// f.getContentPane().add(backgroundPanel, BorderLayout.CENTER);

				LevelSelectPanel = new JPanel();
				springLayout.putConstraint(SpringLayout.WEST, LevelSelectPanel, 5, SpringLayout.WEST,
						f.getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, LevelSelectPanel, 150, SpringLayout.WEST,
						f.getContentPane());
				springLayout.putConstraint(SpringLayout.NORTH, LevelSelectPanel, 5, SpringLayout.NORTH,
						f.getContentPane());
				springLayout.putConstraint(SpringLayout.SOUTH, LevelSelectPanel, -5, SpringLayout.SOUTH,
						f.getContentPane());
				LevelSelectPanel.setBackground(Color.BLUE);
				f.getContentPane().add(LevelSelectPanel);
				LevelSelectPanel.setLayout(null);

				JList<String> list = new JList<>();
				list.setBounds(12, 12, 125, 400);
				DefaultListModel<String> dlm = new DefaultListModel<>();
				dlm.addElement("001");
				list.setModel(dlm);
				list.setSelectedIndex(0);
				LevelSelectPanel.add(list);

				JPanel panel = new JPanel();
				springLayout.putConstraint(SpringLayout.NORTH, panel, 5, SpringLayout.NORTH, f.getContentPane());
				springLayout.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.EAST, LevelSelectPanel);

				JButton btnNewButton = new JButton("New button");
				btnNewButton.setBounds(12, 429, 127, 36);
				LevelSelectPanel.add(btnNewButton);
				springLayout.putConstraint(SpringLayout.SOUTH, panel, -5, SpringLayout.SOUTH, f.getContentPane());
				springLayout.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, f.getContentPane());
				panel.setBackground(Color.GREEN);
				f.getContentPane().add(panel);

				f.setVisible(true);
				f.validate();
			}
		});
	}
}
