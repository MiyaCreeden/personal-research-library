package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

public class ResearchPaperGUI extends JPanel{

    private static final int WIDTH = 100;
	private static final int HEIGHT = 30;
	private static final int TEXT_INDENT = 30;
	private static final String data[] = {"Paper title"};
	private int displayString;
	private Color fillColor;
	
	/**
	 * Constructor creates interface to display paper
	 */
	public ResearchPaperGUI() {
		displayString = 0;
		fillColor = Color.pink;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(fillColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawChars(data[displayString].toCharArray(), 
				0, 
				data[displayString].length(), 
				TEXT_INDENT, 
				2 * getHeight() / 3);
	}

}
