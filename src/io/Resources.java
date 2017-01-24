package io;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Resources {

    private static Class<? extends Thread> classloader = Thread.currentThread().getClass();

    /**
     * Returns the Document made from the named resource
     *
     * @return Dom document for parsing
     */
    public static Document getDocumentForLevel(String level) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(getInputStream(level));
            doc.getDocumentElement().normalize();
            return doc;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getInputStream(String level) {
        return classloader.getResourceAsStream("/levels/" + level + ".xml");
    }

    /**
     * Gets the picture of the start for the levelname supplied
     *
     * @return Buffered image of a similar name
     */
    public static BufferedImage getImageForLevel(String levelname) {
        InputStream inputstream = classloader.getResourceAsStream("/levelpics/" + levelname + ".png");
        try {
            return ImageIO.read(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not find image " + levelname + ".png");
            System.err.println("Check if you added it.");
        }
        // Will not happen naturally. I will supply the levelpics data.
        return null;
    }

    /**
     * Scales input image so its width is new_width
     *
     * @return Scaled image
     */
    public static BufferedImage scaleImage(BufferedImage imageToScale, int new_width) {
        float ratio = imageToScale.getHeight() / (float) imageToScale.getWidth();
        int new_height = (int) (ratio * new_width);

        BufferedImage newImage = new BufferedImage(new_width, new_height, imageToScale.getType());
        newImage.createGraphics().drawImage(imageToScale, 0, 0, new_width, new_height, null);
        return newImage;
    }

    /**
     * All the levels in res/levels
     *
     * @return Level names (eg 001)
     */
    public static List<String> getLevelNames() {
        InputStream inputstream = classloader.getResourceAsStream("/info/levels.txt");

        Scanner filescanner = new Scanner(inputstream);
        List<String> levels = new ArrayList<>();
        while (filescanner.hasNext()) {
            levels.add(filescanner.nextLine());
        }
        filescanner.close();
        return levels;
    }
}
