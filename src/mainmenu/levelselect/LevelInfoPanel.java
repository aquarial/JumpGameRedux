package mainmenu.levelselect;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.Resources;

public class LevelInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BORDER_BUFFER = 55;// px

    private int width;

    private JLabel label_levelimage;

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
        label_levelimage = new JLabel();
        label_levelimage.setBounds(BORDER_BUFFER, BORDER_BUFFER, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.add(label_levelimage);
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
        label_levelimage.setIcon(new ImageIcon(tmp));

        // TODO add display for current records

    }

}
