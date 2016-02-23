package game;

import game.model.MainModel;

/**
 * Provides a game loop for updating a GamePanel
 * 
 * @author karl
 *
 */
class GameThread extends Thread {

	private GamePanel panel;
	private MainModel model;

	static final long minimumSleepTime = 35;

	GameThread(GamePanel gp, MainModel model) {
		this.panel = gp;
		this.model = model;
	}

	@Override
	public void run() {
		long oldTime = System.currentTimeMillis();
		long newTime;
		long deltaTime;
		while (!model.jumperReachedEnd()) {
			newTime = System.currentTimeMillis();
			deltaTime = newTime - oldTime;
			model.updateModel(minimumSleepTime / 1000.0);
			panel.renderGame();

			if (deltaTime < minimumSleepTime) {
				safesleep(minimumSleepTime - deltaTime);
			}

			oldTime = newTime;
		}
	}

	private void safesleep(long mili) {
		try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}