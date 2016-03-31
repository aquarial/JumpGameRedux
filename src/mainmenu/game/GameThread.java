package mainmenu.game;

import mainmenu.game.model.MainModel;

/**
 * Provides a game loop for updating a GamePanel
 * 
 * @author karl
 *
 */
class GameThread extends Thread {

    private GamePanel panel;
    private MainModel model;

    static final long minimumSleepTime = 33;

    /**
     * Initialize the Thread with references
     * 
     * @param gp
     * @param model
     */
    GameThread(GamePanel gp, MainModel model) {
        this.panel = gp;
        this.model = model;
    }

    /**
     * Runs game loop
     * <p>
     * Updates GamePanel
     */
    @Override
    public void run() {
        long oldTime = System.currentTimeMillis();
        long newTime;
        long deltaTime;

        while (!model.jumperReachedEnd()) {

            model.updateModel(minimumSleepTime / 1000.0);
            panel.repaint();

            newTime = System.currentTimeMillis();
            deltaTime = newTime - oldTime;
            if (deltaTime < minimumSleepTime) {
                safesleep(minimumSleepTime - deltaTime);
            }

            oldTime = newTime;
        }

        // handle game ending
        // (make cyan box fill screen for example)

        safesleep(1000);
        panel.endGame.run();
    }

    /**
     * Remove try-catch clutter
     * <p>
     * I can't really do anything if an exception is actually thrown, so I move
     * the clutter out of the game loop for clarity.
     * 
     * @param mili
     */
    private void safesleep(long mili) {
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}