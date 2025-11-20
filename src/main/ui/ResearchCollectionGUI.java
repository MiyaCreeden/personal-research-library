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
import persistance.JsonReader;
import persistance.JsonWriter;

//TODO: add alaram system credit to all gui classes

// TODO: add graphical component

public class ResearchCollectionGUI extends JFrame {
    private Color fillColor;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXT_INDENT = 30;

    private ResearchCollection c;
    private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    private static final String JSON_STORE = "./data/researchcollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates graphfical interface for reserachcollection
    public ResearchCollectionGUI() {
        c = new ResearchCollection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());
        controlPanel = new JInternalFrame("Control Panel", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("My Research Collection");
        setSize(WIDTH, HEIGHT);

        this.fillColor = Color.pink;
        addMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    // EFFECTS: centres display panel on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public JDesktopPane getDesktop(){
        return desktop;
    }

    // EFFECTS: adds display panels for papers
    private void addPaperDisplayPanel(ResearchPaper paper) {
        ResearchPaperGUI paperGUI = new ResearchPaperGUI(paper, ResearchCollectionGUI.this);

        desktop.add(paperGUI, BorderLayout.NORTH);
    }

    // EFFECTS: adds menu
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

    // EFFECTS: adds items to menu
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    // EFFECTS: handles adding a paper to panel
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

            if (titleLoc != null && authorLoc != null && displineLoc != null) {
                ResearchPaper p = new ResearchPaper(titleLoc, authorLoc, displineLoc);
                c.addPaper(p);
            

            }
        }
    }

    // EFFECTS: displays collection on panel
    private class ViewCollectionAction extends AbstractAction {

        ViewCollectionAction() {
            super("View Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            ViewCollection view = new ViewCollection(c, ResearchCollectionGUI.this);
            JInternalFrame collectionFrame = new JInternalFrame("My Collection", false, true, false, false);
            collectionFrame.getContentPane().setLayout(new BorderLayout());
            collectionFrame.getContentPane().add(view, BorderLayout.CENTER);
            collectionFrame.setVisible(true);
            collectionFrame.setSize(700, 500);
            collectionFrame.setLocation(50,50);
            
            desktop.add(collectionFrame);
            desktop.revalidate();
            
            
            
        }
    }

    // EFFECTS: searches through collection and displays search result (paper
    // panels)
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

            if (searchLoc != null) {
                c.filterCollection(searchLoc);
                // TODO: display results of search

            }
        }
    }

    // EFFECTS: saves collection to data base
    private class SaveCollectionAction extends AbstractAction {

        SaveCollectionAction() {
            super("Save Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            if (c == null) {
            JOptionPane.showMessageDialog(ResearchCollectionGUI.this, "No collection to save.",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                jsonWriter.open();
                jsonWriter.write(c);
                jsonWriter.close();
                System.out.println("Saved my papers to " + JSON_STORE);
                
            JOptionPane.showMessageDialog(ResearchCollectionGUI.this,
                    "Collection saved to " + JSON_STORE, "Save Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
                JOptionPane.showMessageDialog(ResearchCollectionGUI.this, "Could not save",
                    "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // EFFECTS: reloads collection and displays it on panels
    private class LoadCollectionAction extends AbstractAction {

        LoadCollectionAction() {
            super("Load Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                c = jsonReader.read();
                System.out.println("Loaded " + "My papers" + " from " + JSON_STORE);
                JOptionPane.showMessageDialog(ResearchCollectionGUI.this,
                    "Collection loaded from " + JSON_STORE, "Load Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
                JOptionPane.showMessageDialog(ResearchCollectionGUI.this, "Could not load.",
                    "Loading Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    // EFFECTS: recieves mouse events
    private class DesktopFocusAction extends MouseAdapter {

        public void mouseClicked(MouseEvent evt) {
            ResearchCollectionGUI.this.requestFocusInWindow();
        }
    }

    // EFFECTS: starts the application
    public static void main(String[] args) {
        new ResearchCollectionGUI();
    }

}
