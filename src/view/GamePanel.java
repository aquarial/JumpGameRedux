package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		System.out.println("print");
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.BLACK);

		g2.fillRect(10, 10, 40, 70);
	}
}
