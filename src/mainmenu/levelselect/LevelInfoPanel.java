package mainmenu.levelselect;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.Resources;

public class LevelInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BORDER_BUFFER = 55;// px
    private static final int labelHeight = 450; // px
    private int width;

    private JLabel lLevelimage;
    private JLabel lLeastjumpsText;
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

        lLeastjumpsText = new JLabel();
        lLeastjumpsText.setBounds(100, labelHeight, 200, 80);
        this.add(lLeastjumpsText);

        lFastestTimeText = new JLabel();
        lFastestTimeText.setBounds(440, labelHeight, 200, 80);
        this.add(lFastestTimeText);
        System.out.println(height);
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

        lLeastjumpsText.setText("Least Jumps " + "999");
        lFastestTimeText.setText("Fastest Time " + "99:99");
    }

}
