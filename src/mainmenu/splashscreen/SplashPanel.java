package mainmenu.splashscreen;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.*;

import mainmenu.RunCode;

public class SplashPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private RunCode changeMainMenu;
    private float alphavalue;
    private BufferedImage splashimage;
    private Graphics2D g2splashimage;
    private WritableRaster imageraster;
    private float[] image_alpha_values;

    private int width;
    private int height;

    /**
     * Initialize SplashPanel
     */
    public SplashPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setOpaque(false);

        alphavalue = 1;

        createSplashImage();

        // image_alpha_values is a reference to the alpha data of splashimage.
        // see paintComponent
        image_alpha_values = new float[width * height];
        imageraster = splashimage.getAlphaRaster();
        imageraster.getPixels(0, 0, width, height, image_alpha_values);

        // When SplashPanel is on top, we don't want anything 'under' it to
        // recieve clicks. So, consume them
        this.addMouseListener(new MouseAdapter() {
            // extends MouseAdapter, overrides nothing
        });
    }

    /**
     * Starts the thread that fades this
     */
    public void waitThenFade() {
        new Thread() {
            public void run() {
                try {

                    Thread.sleep(500);
                    int reps = 200;
                    for (int i = 0; i < reps; i++) {
                        Thread.sleep(1500 / reps);
                        SplashPanel.this.changeSplashAlphaBy(-1.0f / reps);
                        SplashPanel.this.repaint();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                changeMainMenu.run();
            }
        }.start();
    }

    /**
     * Do not call directly
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Updates each pixel's alpha value
        for (int index = 0; index < image_alpha_values.length; index++) {
            image_alpha_values[index] = alphavalue * 255f;
        }
        imageraster.setPixels(0, 0, width, height, image_alpha_values);

        g.drawImage(splashimage, 0, 0, null);
    }

    /**
     * Pass the closure for how to change mainmenu to levelselect
     *
     * @see #waitThenFade()
     */
    public void setOnRunFunction(RunCode runCode) {
        changeMainMenu = runCode;
    }

    /**
     * Call repaint after this method
     */
    public void changeSplashAlphaBy(float delta_alpha) {
        alphavalue += delta_alpha;
    }

    /**
     * Eventually this will read a splash image from res
     */
    private void createSplashImage() {
        splashimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2splashimage = (Graphics2D) splashimage.getGraphics();
        g2splashimage.setBackground(new Color(1f, 1f, 1f, 1f));
        g2splashimage.clearRect(0, 0, width, height);
    }

}
