package mainmenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

import mainmenu.game.GamePanel;
import mainmenu.levelselect.SelectPanel;
import mainmenu.savestates.GameSave;
import mainmenu.splashscreen.SplashPanel;

public class MainMenu {

    private JPanel contentPanel;
    private SplashPanel splashpanel;
    private SelectPanel selectpanel;
    private GamePanel gamepanel;
    private GameSave save;

    private int width = 800;
    private int height = 600;

    /**
     * Runs the program
     */
    public MainMenu() {

        JFrame f = new JFrame("Jump Game Redux");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(width, height);
        f.setResizable(false);

        save = new GameSave();

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        f.getContentPane().add(contentPanel, BorderLayout.CENTER);
        f.setVisible(true);
        f.validate();

        selectpanel = new SelectPanel(width, height, save);
        selectpanel.addActionListenerToStartLevel((ActionEvent e) -> initState(MenuState.PLAY_GAME));
        selectpanel.setLevels(save.getLevelNames());

        splashpanel = new SplashPanel(width, height);
        splashpanel.setOnRunFunction(() -> initState(MenuState.LEVEL_SELECT));
        splashpanel.setFadeBackground(createImage(selectpanel));

        gamepanel = new GamePanel(width, height);
        gamepanel.setOnFinish(() -> initState(MenuState.LEVEL_SELECT));

        initState(MenuState.SPLASH_SCREEN);
    }

    /**
     * @return Renders argument onto a new BufferedImage
     */
    private BufferedImage createImage(JPanel panel) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        panel.setSize(width, height);
        layoutComponent(panel);
        panel.print(g);
        return bi;
    }

    private void layoutComponent(Component component) {
        synchronized (component.getTreeLock()) {
            component.doLayout();

            if (component instanceof Container) {
                for (Component child : ((Container) component).getComponents()) {
                    layoutComponent(child);
                }
            }
        }
    }

    /**
     * Resets the menu. Sets up parts ready for the state provided
     */
    private void initState(MenuState newstate) {
        contentPanel.removeAll();
        switch (newstate) {
            case SPLASH_SCREEN:
                contentPanel.add(splashpanel);
                splashpanel.waitThenFade();
                break;
            case LEVEL_SELECT:
                save.updateLevelRecord(gamepanel.getModelHistory());
                selectpanel.refreshLevelRecords();
                contentPanel.add(selectpanel);
                break;
            case PLAY_GAME:
                gamepanel.startlevel(selectpanel.getSelectedLevel());
                contentPanel.add(gamepanel);
                gamepanel.requestFocusInWindow();
                break;
            default:
                break;
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

}
