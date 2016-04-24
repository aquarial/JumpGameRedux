package mainmenu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import mainmenu.game.GamePanel;
import mainmenu.levelselect.SelectPanel;
import mainmenu.savestates.GameSave;
import mainmenu.splashscreen.SplashPanel;

public class MainMenu {

    private JLayeredPane contentPanel;
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

        JFrame f = new JFrame("Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);
        f.setResizable(false);

        save = new GameSave();

        contentPanel = new JLayeredPane();
        f.getContentPane().add(contentPanel, BorderLayout.CENTER);
        f.setVisible(true);
        f.validate();

        splashpanel = new SplashPanel(width, height);
        splashpanel.setOnRunFunction(waitSecondsThenGoToSelectPanel());

        selectpanel = new SelectPanel(width, height, save);
        selectpanel.addActionListenerToStartLevel(goToGameStartLevel());
        selectpanel.setLevels(save.getLevelNames());

        gamepanel = new GamePanel(width, height);
        gamepanel.setOnFinish(goToSelectPanel());

        initState(MenuState.SPLASH_SCREEN);
    }

    /**
     * Resets the menu. Sets up parts ready for the state provided
     * 
     * @param newstate
     */
    void initState(MenuState newstate) {
        contentPanel.removeAll();
        switch (newstate) {
        case SPLASH_SCREEN:
            selectpanel.setBounds(0, 0, width, height);
            splashpanel.setBounds(0, 0, width, height);
            contentPanel.add(selectpanel, new Integer(0));
            contentPanel.add(splashpanel, new Integer(1));
            splashpanel.waitThenFade();
            break;
        case LEVEL_SELECT:
            save.updateLevelRecord(gamepanel.getModelHistory());
            selectpanel.setBounds(0, 0, width, height);
            contentPanel.add(selectpanel);
            break;
        case PLAY_GAME:
            gamepanel.setBounds(0, 0, width, height);
            gamepanel.startlevel(selectpanel.getSelectedLevel());
            contentPanel.add(gamepanel);
            break;
        default:
            break;
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Thread that waits, then changes MenuState to LEVEL_SELECT
     * 
     * @return
     */
    public Thread waitSecondsThenGoToSelectPanel() {
        // I can't use use a more functional closure because I use
        // Thread.sleep(), and I don't want to hold up the main thread.
        return new Thread() {
            public void run() {
                try {

                    Thread.sleep(500);
                    int reps = 200;
                    for (int i = 0; i < reps; i++) {
                        Thread.sleep(1500 / reps);
                        splashpanel.changeSplashAlphaBy(-1.0f / reps);
                        splashpanel.repaint();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                initState(MenuState.LEVEL_SELECT);
            }
        };
    }

    /**
     * Closure to change MenuState to LEVEL_SELECT
     * 
     * @return
     */
    public RunCode goToSelectPanel() {
        return () -> {
            initState(MenuState.LEVEL_SELECT);
        };

    }

    /**
     * Closure to change MenuState to PLAY_GAME
     * 
     * @return
     */
    ActionListener goToGameStartLevel() {
        return (ActionEvent e) -> {
            initState(MenuState.PLAY_GAME);
        };
    }

}
