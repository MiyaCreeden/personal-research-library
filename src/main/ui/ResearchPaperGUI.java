package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.w3c.dom.events.MouseEvent;

import model.ResearchPaper;

public class ResearchPaperGUI extends JInternalFrame{

    private static final int WIDTH = 50;
	private static final int HEIGHT = 150;
	private static final int TEXT_INDENT = 30;
	
	
	private Color fillColor;
    private ResearchPaper p;
    private Component parent;
	
	
	//EFFECTS: creates interface to display paper
	 
	public ResearchPaperGUI(ResearchPaper paper, Component parent) {
        
        super(paper.getPTitle(), false, false, false, false);
        this.parent = parent;
        this.p = paper;
		fillColor = Color.pink;
        JButton viewButton = new JButton(new ViewAction());
        viewButton.setBounds(15,70,40,20);

        
        setBackground(fillColor);
        add(viewButton);
        setSize(new Dimension(WIDTH,HEIGHT));
      

    }
        

	

    private class ViewAction extends AbstractAction{

        ViewAction(){
            super("View");
        }

        @Override
        public void actionPerformed(ActionEvent evt){
            add(new AddPaper(p, parent));
        }
    }



   
	
	@Override
	public void paintComponent(Graphics g) {
        //String data[] = {p.getPTitle()};
		super.paintComponent(g);
       
		g.setColor(fillColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//g.drawChars(data[displayString].toCharArray(), 
		//		0, 
		//		data[displayString].length(), 
		//		TEXT_INDENT, 
		//		2 * getHeight() / 3);

        g.drawString(p.getPTitle(), TEXT_INDENT, 20);

        
	}


	//public void update(boolean isRead) {
	//	displayString = (isRead ? 1 : 0);
	//	fillColor = (isRead ? Color.GREEN : Color.PINK);
	//	repaint();
	//}




    

}
