package mainmenu.levelselect;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.Resources;

public class LevelInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BORDER_BUFFER = 35;// px

    private int width;

    private JLabel l_levelimage;

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
        l_levelimage = new JLabel();
        l_levelimage.setBounds(BORDER_BUFFER, BORDER_BUFFER, IMAGE_WIDTH, IMAGE_HEIGHT);
        this.add(l_levelimage);
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
        l_levelimage.setIcon(new ImageIcon(tmp));
    }

}
