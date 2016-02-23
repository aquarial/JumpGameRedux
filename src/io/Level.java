package io;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Point;
import util.Quad;

public class Level {

	private static Resources r = new Resources();

	private Point playerPosition;
	private ArrayList<Quad> allQuads;

	/**
	 * Builds a level from the an xml file of the level name.
	 * <p>
	 * Provides methods to access data about that level.
	 * 
	 * @param levelName
	 */
	public Level(String levelName) {
		Document level = r.getDocumentForLevel(levelName);
		level.normalize();
		clean(level);

		Node player = level.getElementsByTagName("player").item(0);
		playerPosition = parsePlayerPosition(player);

		NodeList listOfQuads = level.getElementsByTagName("block");
		allQuads = new ArrayList<Quad>();
		for (int index = 0; index < listOfQuads.getLength(); index++) {
			Quad oneQuad = parseQuads(listOfQuads.item(index));
			allQuads.add(oneQuad);
		}

	}

	/**
	 * 
	 * Parses player coordinate from xml node
	 * 
	 * @param playerNode
	 * @return Point representing coordinate
	 */
	Point parsePlayerPosition(Node playerNode) {
		double xpos = Double.parseDouble(playerNode.getFirstChild()
				.getTextContent());
		double ypos = Double.parseDouble(playerNode.getLastChild()
				.getTextContent());
		return new Point(xpos, ypos);

	}

	/**
	 * 
	 * Parses the corner coordinates from xml node
	 * 
	 * @param childOfLevelData
	 * @return The coordinates the child held
	 */
	Quad parseQuads(Node childOfLevelData) {
		NodeList childern = childOfLevelData.getChildNodes();
		double[] data = new double[4];
		for (int index = 0; index < 4; index++) {
			data[index] = Double.parseDouble(childern.item(index)
					.getTextContent());
		}
		return new Quad(data);
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
	 * @return the corners of the quads
	 */
	public ArrayList<Quad> getQuadData() {
		return allQuads;
	}

}
