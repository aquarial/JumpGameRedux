package mainmenu.game;

import mainmenu.game.model.MainModel;

/**
 * Provides a game loop for updating a GamePanel
 *
 * @author karl
 */
class GameThread extends Thread {

    private GamePanel panel;
    private MainModel model;

    static final long minimumSleepTime = 33;

    /**
     * Initialize the Thread with references
     */
    GameThread(GamePanel gp, MainModel model) {
        this.panel = gp;
        this.model = model;
    }

    /**
     * <b>CALL USING start()</b>
     * <p>
     * Runs game loop
     */
    @Override
    public void run() {
        long oldTime = System.currentTimeMillis();
        long newTime;
        long deltaTime;

        while (!model.levelComplete()) {
            model.updateModel(minimumSleepTime / 1000.0);
            panel.repaint();

            newTime = System.currentTimeMillis();
            deltaTime = newTime - oldTime;

            if (deltaTime < minimumSleepTime) {
                try {
                    Thread.sleep(minimumSleepTime - deltaTime);
                } catch (InterruptedException e) {
                    return; // IF interrupted THEN return;
                }
            }

            oldTime = newTime;
        }

        // handle game ending

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return; // IF interrupted THEN return;
        }
        panel.endGame.run();
    }

}