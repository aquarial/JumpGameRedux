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

	void printNodes(String name, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			System.out.println(name + "[" + i + "]" + " = " + n.getNodeName());
		}
	}

	/**
	 * src="http://www.sitepoint.com/removing-useless-nodes-from-the-dom/"
	 * 
	 * Removes whitespace and #text nodes
	 * 
	 * @param node Node to clean
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

}
