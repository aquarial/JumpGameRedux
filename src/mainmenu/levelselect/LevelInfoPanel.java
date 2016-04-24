package mainmenu.levelselect;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.Resources;

public class LevelInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BORDER_BUFFER = 55;
    private static final int label_y_pos = 450;
    private static final int label_x_distance = 150;
    private static final int label_height = 80;
    private static final int label_width = 100;
    private int width;

    private JLabel lLevelimage;
    private JLabel lLeastJumpsText;
    private JLabel lFastestTimeText;

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

        lLeastJumpsText = new JLabel();
        lLeastJumpsText.setBounds(label_x_distance, label_y_pos, label_width, label_height);
        this.add(lLeastJumpsText);

        lFastestTimeText = new JLabel();
        int fastestXPos = width - label_x_distance - label_width;
        lFastestTimeText.setBounds(fastestXPos, label_y_pos, label_width, label_height);
        this.add(lFastestTimeText);
    }

    /**
     * Displays stats and image for the level
     * 
     * @param levelname
     *            name of the selected level
     */
    void setupPanelForLevel(String levelname) {
        BufferedImage tmp = Resources.getImageForLevel(levelname);
        tmp = Resources.scaleImage(tmp, width - 2 * BORDER_BUFFER);
        lLevelimage.setIcon(new ImageIcon(tmp));

        lLeastJumpsText.setText("Least Jumps ");
        lFastestTimeText.setText("Fastest Time");
    }

}
