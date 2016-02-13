package view;

import java.awt.Color;
import javax.swing.JPanel;
import org.w3c.dom.Document;
import io.Resources;

public class JPanelConstructor {
	public static JPanel makeNewGamePanel() {
		Resources r = new Resources();
		Document root = r.getDocumentForLevel("1");

		System.out.println("baseURI = " + root.getBaseURI());

		JPanel pan = new JPanel();
		pan.setBackground(Color.BLUE);
		return pan;
	}
}
