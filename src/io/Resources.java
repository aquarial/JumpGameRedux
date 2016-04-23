package io;

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

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Resources {

    private static Class<? extends Thread> classloader = Thread.currentThread().getClass();

    /**
     * Returns the Document made from the named resource
     * 
     * @param level
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

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getInputStream(String level) {
        return classloader.getResourceAsStream("/levels/" + level + ".xml");
    }

    public BufferedImage getImageForLevel(String level) {
        InputStream inputstream = classloader.getResourceAsStream("/levelpics/" + level + ".png");
        try {
            return ImageIO.read(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not find image " + level + ".png");
            System.err.println("Check if you added it.");
        }
        // Will not happen naturally. I will supply the levelpics data.
        return null;
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
