package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import model.ResearchPaper;

public class ResearchPaperGUI extends JPanel{

    private static final int WIDTH = 30;
	private static final int HEIGHT = 100;
	private static final int TEXT_INDENT = 30;
	private final String data[] = {"Paper Title"}; //TODO: change so it shows papers title
	private int displayString;
	private Color fillColor;
    private ResearchPaper p;
	
	
	//EFFECTS: creates interface to display paper
	 
	public ResearchPaperGUI() {
        
		displayString = 0;
		fillColor = Color.pink;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
	
	@Override
	public void paint(Graphics g) {
        //String data[] = {p.getPTitle()};
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
