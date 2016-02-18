package model;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.Resources;

public class Level {

	private static Resources r = new Resources();

	private Point playerPosition;
	private ArrayList<Quad> stickyQuads;

	/**
	 * Builds a level based off of a the xml file of the same name.
	 * 
	 * @param levelName
	 */
	public Level(String levelName) {
		Document level = r.getDocumentForLevel(levelName);
		level.normalize();
		clean(level);

		Node player = level.getElementsByTagName("player").item(0);
		double xpos = Double.parseDouble(player.getFirstChild().getTextContent());
		double ypos = Double.parseDouble(player.getLastChild().getTextContent());
		playerPosition = new Point(xpos, ypos);

		NodeList listOfQuadNodes = level.getElementsByTagName("quad");
		stickyQuads = new ArrayList<Quad>();
		for (int index = 0; index < listOfQuadNodes.getLength(); index++) {
			double[] cornerData = parseQuadCorners(listOfQuadNodes.item(index));
			stickyQuads.add(new Quad(cornerData));
		}

	}

	/**
	 * 
	 * Parses the corner coordinates from the xml
	 * 
	 * @param childOfLevelData
	 * @return The coordinates the child held
	 */
	double[] parseQuadCorners(Node childOfLevelData) {
		NodeList childern = childOfLevelData.getChildNodes();
		double[] data = new double[4];
		for (int index = 0; index < 4; index++) {
			data[index] = Double.parseDouble(childern.item(index).getTextContent());
		}
		return data;
	}

	/**
	 * src="http://www.sitepoint.com/removing-useless-nodes-from-the-dom/"
	 * 
	 * Removes whitespace and #text nodes
	 * 
	 * @param node
	 *            Node to clean
	 */
	private void clean(Node node) {
		NodeList childNodes = node.getChildNodes();

		for (int n = childNodes.getLength() - 1; n >= 0; n--) {
			Node child = childNodes.item(n);
			short nodeType = child.getNodeType();

			if (nodeType == Node.ELEMENT_NODE)
				clean(child);
			else if (nodeType == Node.TEXT_NODE) {
				String trimmedNodeVal = child.getNodeValue().trim();
				if (trimmedNodeVal.length() == 0)
					node.removeChild(child);
				else
					child.setNodeValue(trimmedNodeVal);
			} else if (nodeType == Node.COMMENT_NODE)
				node.removeChild(child);
		}
	}

	/**
	 * @return the playerPosition
	 */
	public Point getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * @return the stickyQuads
	 */
	public ArrayList<Quad> getStickyQuads() {
		return stickyQuads;
	}

}
