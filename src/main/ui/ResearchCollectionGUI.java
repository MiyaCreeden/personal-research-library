package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.w3c.dom.events.MouseEvent;

import model.ResearchCollection;
import model.ResearchPaper;

//TODO: add credit to all gui classes
//TODO: add method specification

public class ResearchCollectionGUI extends JFrame{
    private Color fillColor;
    private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final int TEXT_INDENT = 30;

    private ResearchCollection c;
    private JComboBox<String> printCombo;
	private JDesktopPane desktop;
	private JInternalFrame controlPanel;
    
    //EFFECTS: creates graphfical interface for reserachcollection
    public ResearchCollectionGUI(){
        c = new ResearchCollection();

        desktop = new JDesktopPane();
		desktop.addMouseListener(new DesktopFocusAction());
		controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
		controlPanel.setLayout(new BorderLayout());
		
		setContentPane(desktop);
		setTitle("My Research Collection");
		setSize(WIDTH, HEIGHT);
		
		
		addMenu();
		addPaperDisplayPanel();
		
		
		controlPanel.pack();
		controlPanel.setVisible(true);
		desktop.add(controlPanel);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		centreOnScreen();
		setVisible(true);
    }

   private void centreOnScreen() {
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
	}

    private void addPaperDisplayPanel() {
        ResearchPaperGUI paperGUI = new ResearchPaperGUI();
		controlPanel.add(paperGUI, BorderLayout.NORTH);
    }

   

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Menu");
		mainMenu.setMnemonic('S');
		addMenuItem(mainMenu, new AddPaperAction(), null);
        addMenuItem(mainMenu, new ViewCollectionAction(), null);
		addMenuItem(mainMenu, new SearchCollectionAction(), null);
        addMenuItem(mainMenu, new SaveCollectionAction(), null);
		addMenuItem(mainMenu, new LoadCollectionAction(), null);
		menuBar.add(mainMenu);
		
		setJMenuBar(menuBar);
    }

    

     
	private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
		JMenuItem menuItem = new JMenuItem(action);
		menuItem.setMnemonic(menuItem.getText().charAt(0));
		menuItem.setAccelerator(accelerator);
		theMenu.add(menuItem);
	}

    
    

    private class AddPaperAction extends AbstractAction {
		
		AddPaperAction() {
			super("Add Paper");
		}
		

        @Override
        public void actionPerformed(ActionEvent evt) {
            String titleLoc = JOptionPane.showInputDialog(null,
					  "Title of Paper?",
					  "Enter papers title",
					  JOptionPane.QUESTION_MESSAGE);

            String authorLoc = JOptionPane.showInputDialog(null,
					  "Author of Paper?",
					  "Enter papers author",
					  JOptionPane.QUESTION_MESSAGE);

             String displineLoc = JOptionPane.showInputDialog(null,
					  "Displine of Paper?",
					  "Enter papers displine",
					  JOptionPane.QUESTION_MESSAGE);

                      
			try {
				if (titleLoc != null && authorLoc != null && displineLoc != null) {
					ResearchPaper p = new ResearchPaper(titleLoc, authorLoc, displineLoc);
					desktop.add(new AddPaper(p, ResearchCollectionGUI.this));
				}
			} catch (DuplicatePaperException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Already added", 
						JOptionPane.ERROR_MESSAGE);
			}
        }
	}

    private class ViewCollectionAction extends AbstractAction {
		
		ViewCollectionAction() {
			super("View Collection");
		}
		

        @Override
        public void actionPerformed(ActionEvent evt) {
            //TODO: implement viewing list collection -> add graphical component
        }
	}

    private class SearchCollectionAction extends AbstractAction {
		
		SearchCollectionAction() {
			super("Search Collection");
		}
		

        @Override
        public void actionPerformed(ActionEvent evt) {
            String searchLoc = JOptionPane.showInputDialog(null,
					  "Search by Title, Author, or Displine",
					  "Enter search term",
					  JOptionPane.QUESTION_MESSAGE);

                      
			try {
				if (searchLoc != null) {
					c.filterCollection(searchLoc);
					// TODO: display results of search 
				}
			} catch (InvalidSearchException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid search term", 
						JOptionPane.ERROR_MESSAGE);
			}
        }
    }

    private class SaveCollectionAction extends AbstractAction {
		
		SaveCollectionAction() {
			super("Save Collection");
		}
		

        @Override
        public void actionPerformed(ActionEvent evt) {
            
                      
			try {
				//TODO: implement to save
				
			} catch(FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Already added", 
						JOptionPane.ERROR_MESSAGE);
			}
        }

    }

     private class LoadCollectionAction extends AbstractAction {
		
		LoadCollectionAction() {
			super("Load Collection");
		}
		



        @Override
            public void actionPerformed(ActionEvent evt) {
                
            
        

                      
			try {
				// TODO:implement loading data
				
			} catch(IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Loading Error", 
						JOptionPane.ERROR_MESSAGE);
			}
            
        }
	}
        
	

    
		
    private class DesktopFocusAction extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			ResearchCollectionGUI.this.requestFocusInWindow();
		}
	}

    //EFFECTS: starts the application
	public static void main(String[] args) {
		new ResearchCollectionGUI();
	}
    



    

    
    
 

}
