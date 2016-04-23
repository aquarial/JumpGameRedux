package mainmenu.levelselect;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JButton startLevelButton;
    private JComboBox<String> levelSelecter;

    /**
     * Constructs all of the components that make up the level select panel
     */
    public SelectPanel(int width, int height) {

        setLayout(null);

        // *******LEFT HAND SIDE
        JPanel levelSelectPanel = new JPanel();
        levelSelectPanel.setBounds(0, 0, 130, height);
        levelSelectPanel.setBackground(new Color(180, 180, 180));

        // 1st element - "Level" label
        JLabel showLevel = new JLabel("Level");
        levelSelectPanel.add(showLevel);

        // 2nd element - "Select level" combo box
        levelSelecter = new JComboBox<>();
        levelSelecter.setToolTipText("Choose level to start");
        levelSelectPanel.add(levelSelecter);

        // 3rd element - "Play Level" Button
        startLevelButton = new JButton("Start Level");
        startLevelButton.setToolTipText("Start selected level");
        levelSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        levelSelectPanel.add(startLevelButton);

        //
        // ********MIDDLE BOX
        LevelInfoPanel levelInfoPanel = new LevelInfoPanel();
        levelInfoPanel.setBounds(130, 0, width - 130, height);
        levelInfoPanel.setBackground(Color.GREEN);

        add(levelSelectPanel);
        add(levelInfoPanel);

    }

    /**
     * Update level select combobox with unlocked levels
     * 
     * @param state
     */
    public void setLevels(List<String> state) {
        for (String level : state) {
            levelSelecter.addItem(level);
        }
    }

    /**
     * Action listener to <i>start level</i> button
     * 
     * @param listner
     */
    public void addActionListenerToStartLevel(ActionListener listener) {
        startLevelButton.addActionListener(listener);
    }

    /**
     * @return Name of the level selected to play
     */
    public String getSelectedLevel() {
        return levelSelecter.getSelectedItem().toString();
    }

}
