package mainmenu.levelselect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import io.Resources;

public class SelectPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JButton startLevelButton;
    private JComboBox<String> levelSelecter;

    /**
     * Constructs all of the components that make up the level select panel
     */
    public SelectPanel() {

        this.setLayout(new BorderLayout());
        // *******LEFT HAND SIDE
        JPanel LevelSelectPanel = new JPanel();
        LevelSelectPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        LevelSelectPanel.setBackground(Color.BLUE);

        // 1st element - "Play Level" Button
        startLevelButton = new JButton("Start Level");
        LevelSelectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        LevelSelectPanel.add(startLevelButton);

        // 2nd element - "Select level" combo box
        levelSelecter = new JComboBox<>();
        for (String filename : Resources.getLevelNames()) {
            String withoutxml = filename.substring(0, filename.length() - 4);
            levelSelecter.addItem(withoutxml);
        }
        LevelSelectPanel.add(levelSelecter);

        //
        // ********MIDDLE BOX
        JPanel levelInfoPanel = new JPanel();
        levelInfoPanel.setBackground(Color.GREEN);

        add(LevelSelectPanel, BorderLayout.WEST);
        add(levelInfoPanel, BorderLayout.CENTER);

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
