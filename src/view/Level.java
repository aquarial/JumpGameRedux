package view;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.Resources;

public class Level {

	private static Resources r = new Resources();

	/**
	 * Builds a level based off of a the xml file of the same name.
	 * 
	 * @param levelName
	 */
	public Level(String levelName) {
		Document level = r.getDocumentForLevel(levelName);
		NodeList nodes = level.getChildNodes();

		Node player = nodes.item(1);
		Node p_location = player.getFirstChild();
		Node p_loc_x = p_location.getFirstChild();
		Node p_loc_y = p_loc_x.getNextSibling();

		System.out.println("x = " + p_loc_x.getNodeValue());
		System.out.println("y = " + p_loc_y.getNodeValue());

		Node quads = nodes.item(2);
	}

}
