package mainmenu.game.model.level;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import io.Resources;

/**
 * POJO for xml levels
 */
public class LevelData {

    /**
     * Builds a level from the an xml file of the level name.
     * <p>
     * Provides methods to access data about that level.
     *
     * @param levelName
     */
    public static LevelData parseLevelFromName(String levelName) {
        return new LevelData(levelName);
    }

    private LevelData(String levelName) {
        Document level = Resources.getDocumentForLevel(levelName);
        level.normalize();
        clean(level);

        // Player
        Node player = level.getElementsByTagName("player").item(0);
        playerPosition = parsePlayerPosition(player);

        // Blocks
        NodeList listOfQuads = level.getElementsByTagName("block");
        allQuads = new ArrayList<Quad>();
        for (int index = 0; index < listOfQuads.getLength(); index++) {
            Quad oneQuad = parseQuads(listOfQuads.item(index));
            allQuads.add(oneQuad);
        }

        // Finish block
        finishQuad = parseQuads(
                level.getElementsByTagName("finishblock").item(0));
    }

    private Point playerPosition;
    private ArrayList<Quad> allQuads;
    private Quad finishQuad;

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

    /**
     *
     * @return Quad representing finish area
     */
    public Quad getFinishQuad() {
        return finishQuad;
    }

    /**
     *
     * Parses player coordinate from xml node
     *
     * @param playerNode
     * @return Point representing coordinate
     */
    private Point parsePlayerPosition(Node playerNode) {
        double xpos = Double
                              .parseDouble(playerNode.getFirstChild().getTextContent());
        double ypos = Double
                              .parseDouble(playerNode.getLastChild().getTextContent());
        return new Point(xpos, ypos);

    }

    /**
     *
     * Parses the corner coordinates from xml node
     *
     * @param childOfLevelData
     * @return The coordinates the child held
     */
    private Quad parseQuads(Node childOfLevelData) {
        NodeList childern = childOfLevelData.getChildNodes();
        double[] data = new double[4];
        for (int index = 0; index < 4; index++) {
            data[index] = Double
                                  .parseDouble(childern.item(index).getTextContent());
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
     * Allows me to scale a completed level easily. This method should not be
     * called during normal game runtime.
     */
    public String toString() {
        double factor = 1;
        //@formatter:off
        StringBuilder sb = new StringBuilder();
        sb.append("<root>\n\n"
                + "<name>Basic level</name>\n\n"
                + "<player>\n"
                + "<xpos>" + playerPosition.getX()*factor + "</xpos>\n"
                + "<ypos>"  + playerPosition.getY()*factor + "</ypos>\n"
                + "</player>\n"
                + "<leveldata>"
                );
        
        for (Quad quad : allQuads) {
            sb.append(quadToString("block",factor, quad));
        }
        
        sb.append(quadToString("finishblock",factor, finishQuad));
        
        sb.append("</leveldata>\n"
                + "</root>");
        
        //@formatter:on
        return sb.toString();
    }

    private String quadToString(String name, double factor, Quad quad) {
        //@formatter:off
        return "\n" + 
                "       <"+name+">\n" + 
                "           <x1>"+quad.getX1()*factor+"</x1>\n" + 
                "           <y1>"+quad.getY1()*factor+"</y1>\n" + 
                "           <x2>"+quad.getX2()*factor+"</x2>\n" + 
                "           <y2>"+quad.getY2()*factor+"</y2>\n" + 
                "       </"+name+">"
                + "\n\n";
        //@formatter:on
    }

}
