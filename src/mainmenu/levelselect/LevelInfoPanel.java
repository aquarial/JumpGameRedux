package mainmenu.levelselect;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

import javax.swing.JPanel;

import io.Resources;

public class LevelInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final int BORDER_BUFFER = 30;// px

    private int width;
    private int height;

    private BufferedImage panel;
    private Graphics2D g2_panel;

    private BufferedImage levelimage;
    private Graphics2D g2_levelimage;

    /**
     * @param width
     * @param height
     */
    LevelInfoPanel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Displays stats and image for the level
     * 
     * @param levelname
     *            name of the selected level
     */
    void setupPanelForLevel(String levelname) {
        levelimage = Resources.getImageForLevel(levelname);
        levelimage = Resources.scaleImage(levelimage, width - 2 * BORDER_BUFFER);
        g2_panel.drawImage(levelimage, BORDER_BUFFER, BORDER_BUFFER, null);
        this.repaint();
    }

}
