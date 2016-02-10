package graphics;

import io.Resources;

import java.awt.Color;

import javax.swing.JPanel;

import org.w3c.dom.Element;

public class JPanelConstructor {
	public static JPanel makeNewGamePanel() {
		Resources r = new Resources();
		Element root = r.getDocumentForLevel(1);

		JPanel pan = new JPanel();
		pan.setBackground(Color.BLUE);
		return pan;
	}
}
