package io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Resources {

	public void getDocumentForLevel(int level) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(getInputFile(level));
			doc.getDocumentElement().normalize();
			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getInputFile(int level) {
		Class<? extends Thread> classloader = Thread.currentThread().getClass();

		return new File(classloader.getResource("/levels/001.xml").getPath());
	}
}
