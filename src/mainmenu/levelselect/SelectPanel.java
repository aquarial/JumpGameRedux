package mainmenu.levelselect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SelectPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> levelSelecter;

	public SelectPanel() {
		//
		// *******LEFT HAND SIDE
		JPanel LevelSelectPanel = new JPanel();
		LevelSelectPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		LevelSelectPanel.setBackground(Color.BLUE);

		// 1st element - "Play Level" Button
		JButton startLevelButton = new JButton("Start Level");
		// startLevelButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// initState(MenuState.PLAY_GAME);
		// }
		// });
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

		add(LevelSelectPanel, BorderLayout.WEST);
		add(levelInfoPanel, BorderLayout.CENTER);

	}

}