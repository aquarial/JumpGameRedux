package io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Resources {

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

    static InputStream getInputStream(String level) {
        Class<? extends Thread> classloader = Thread.currentThread().getClass();
        return classloader.getResourceAsStream("/levels/" + level + ".xml");
    }

    /**
     * All the level data files in the resources directory
     * @return
     */
    public static List<String> getLevelNames() {
        File levelsDir = new File("res/levels");
        List<String> levels = Arrays.asList(levelsDir.list());
        Collections.sort(levels);
        return levels;
    }
}
