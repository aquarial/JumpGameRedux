package io;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Resources {

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

	public static InputStream getInputStream(String level) {
		Class<? extends Thread> classloader = Thread.currentThread().getClass();
		return classloader.getResourceAsStream("/levels/" + level + ".xml");
	}
}
