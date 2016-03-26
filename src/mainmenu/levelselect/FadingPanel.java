package mainmenu.levelselect;

import java.awt.Color;

import javax.swing.JPanel;

public class FadingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Color start;
	Color end;
	long transitiontime;
	float progress;

	public FadingPanel(Color start, Color end, long miliseconds) {
		this.setOpaque(false);
		this.start = start;
		this.end = end;
		this.transitiontime = miliseconds;
	}

	void fadeBy(float delta_progress) {
		progress += delta_progress;
		//@formatter:off
		int newRed =   (int) (progress * start.getRed()   + (1 - progress) * end.getRed()  );
		int newGreen = (int) (progress * start.getGreen() + (1 - progress) * end.getGreen());
		int newBlue =  (int) (progress * start.getBlue()  + (1 - progress) * end.getBlue()  );
		int newAlpha = (int) (progress * start.getAlpha() + (1 - progress) * end.getAlpha());
		//@formatter:on
		this.setBackground(new Color(newRed, newGreen, newBlue, newAlpha));
	}

	public FadingPanel startTransition() {
		progress = 0;
		new UpdateThread(this, transitiontime).run();
		return this;
	}

}

class UpdateThread implements Runnable {

	private FadingPanel fadingpanel;
	private long transitiontime;

	UpdateThread(FadingPanel fp, long transitiontime) {
		this.fadingpanel = fp;
		this.transitiontime = transitiontime;
	}

	@Override
	public void run() {
		int step_size = 33;
		for (int i = 0; i < transitiontime; i += step_size) {
			try {
				Thread.sleep(step_size);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fadingpanel.fadeBy(i * 1.0f / transitiontime);
			fadingpanel.repaint();
		}
	}

}
