package view;

import model.MainModel;

class MyThread extends Thread {

	private GamePanel panel;
	private MainModel model;

	public MyThread(GamePanel gp, MainModel model) {
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
			model.updateModel(deltaTime / 1000.0);
			panel.repaint();

			if (deltaTime < 50) {
				safesleep(50 - deltaTime);
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