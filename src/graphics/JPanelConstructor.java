package graphics;

import io.Resources;

import java.awt.Color;

import javax.swing.JPanel;

public class JPanelConstructor {
	public static JPanel makeNewGamePanel() {
		Resources r = new Resources();
		r.getDocumentForLevel(1);

		JPanel pan = new JPanel();
		pan.setBackground(Color.BLUE);
		return pan;
	}
}
