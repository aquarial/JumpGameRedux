package mainmenu.levelselect;

import java.awt.image.BufferedImage;

import javax.swing.*;

import io.Resources;
import mainmenu.savestates.GameSave;

public class LevelInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BORDER_BUFFER = 55;
    private static final int label_y_pos = 450;
    private static final int label_x_distance = 150;
    private static final int label_height = 20;
    private static final int label_width = 100;
    private int width;

    private JLabel lLevelimage;
    private JLabel lLeastJumpsText;
    private JLabel lLeastJumpsRecord;
    private JLabel lFastestTimeText;
    private JLabel lFastestTimeRecord;

    /**
     * @param width
     * @param height
     */
    LevelInfoPanel(int width, int height) {
        this.width = width;
        // this.height = height;

        this.setLayout(null);

        int IMAGE_WIDTH = width - 2 * BORDER_BUFFER;
        int IMAGE_HEIGHT = (int) (.65 * IMAGE_WIDTH);

        lLevelimage = new JLabel();
        lLevelimage.setBounds(BORDER_BUFFER, BORDER_BUFFER, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.add(lLevelimage);

        // least jumps
        lLeastJumpsText = new JLabel();
        lLeastJumpsText.setBounds(label_x_distance, label_y_pos, label_width, label_height);
        lLeastJumpsText.setText("Least Jumps:");
        this.add(lLeastJumpsText);

        lLeastJumpsRecord = new JLabel();
        lLeastJumpsRecord.setBounds(label_x_distance, label_y_pos + label_height, label_width, label_height);
        this.add(lLeastJumpsRecord);

        // fastest time
        int fastestXPos = width - label_x_distance - label_width;

        lFastestTimeText = new JLabel();
        lFastestTimeText.setText("Fastest Time:");
        lFastestTimeText.setBounds(fastestXPos, label_y_pos, label_width, label_height);
        this.add(lFastestTimeText);

        lFastestTimeRecord = new JLabel();
        lFastestTimeRecord.setBounds(fastestXPos, label_y_pos + label_height, label_width, label_height);
        this.add(lFastestTimeRecord);
    }

    /**
     * Displays stats and image for the level
     *
     * @param levelname name of the selected level
     */
    void setupPanelForLevel(GameSave save, String levelname) {
        BufferedImage tmp = Resources.getImageForLevel(levelname);
        tmp = Resources.scaleImage(tmp, width - 2 * BORDER_BUFFER);
        lLevelimage.setIcon(new ImageIcon(tmp));

        lLeastJumpsRecord.setText(save.leastJumpsForLevel(levelname));
        lFastestTimeRecord.setText(save.fastestTimeForLevel(levelname));
    }

}
