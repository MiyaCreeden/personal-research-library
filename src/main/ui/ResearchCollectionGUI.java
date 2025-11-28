package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import org.w3c.dom.events.MouseEvent;

import model.Event;
import model.EventLog;
import model.ResearchCollection;
import model.ResearchPaper;
import persistance.JsonReader;
import persistance.JsonWriter;

//Credit: code written in this class (and private classes) is inspired by AlarmSystem:
// (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git)

//creates graphical interface for research collection
public class ResearchCollectionGUI extends JFrame implements WindowListener {
    private Color fillColor;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXT_INDENT = 30;

    private ResearchCollection collection;
    private JComboBox<String> printCombo;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    private static final String JSON_STORE = "./data/researchcollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: instantiates the gui
    public ResearchCollectionGUI() {
        collection = new ResearchCollection();
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
            }
        });
        centreOnScreen();
        setVisible(true);
        desktop.setBackground(fillColor);
    }

    // EFFECTS: prints event log after user quits

    private void printEventLog() {
        EventLog events = EventLog.getInstance();
        for (Event evt : events) {
            System.out.println(evt.getDescription());
        }
        System.exit(0);
    }

    // EFFECTS: centres display panel on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    // MODIFIES: this
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

    // Credit: code written in this class was inspired by IconDemo in Oracle
    // (https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html)
    // and photo
    // credit:(https://www.mosio.com/more-clinical-research-memes-mosio-for-research/)

    // handles adding a paper to panel
    private class AddPaperAction extends AbstractAction {

        // EFFECTS: instantiates action with given name
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
                collection.addPaper(p);
                ImageIcon displayIcon = new ImageIcon("src/main/resources/images/addpaperimage.png");
                Image scaled = displayIcon.getImage().getScaledInstance(500, 450, Image.SCALE_SMOOTH);
                ImageIcon img = new ImageIcon(scaled);
                Image thumbImg = img.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                ImageIcon thumbIcon = new ImageIcon(thumbImg);
                VisualComponentAction vca = new VisualComponentAction(img, thumbIcon, "Successfully added paper!");
                vca.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "showImage"));
            }
        }
    }

    // Credit: code written in this class was inspired by IconDemo in Oracle
    // (https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html)

    // displays image on screen whenever paper is added to the collection
    private class VisualComponentAction extends AbstractAction {
        private Icon displayPhoto;
        private Icon thumb;

        // EFFECTS: instantiates action
        public VisualComponentAction(Icon photo, Icon thumb, String desc) {
            super(desc);
            this.displayPhoto = photo;
            this.thumb = thumb;

            putValue(SHORT_DESCRIPTION, desc);
            putValue(LARGE_ICON_KEY, thumb);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JInternalFrame frame = new JInternalFrame("", false, true, false, false);
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(displayPhoto);
            frame.setTitle(getValue(SHORT_DESCRIPTION).toString());
            frame.getContentPane().add(imageLabel, BorderLayout.CENTER);
            frame.setSize(500, 500);
            frame.setLocation(50, 50);
            desktop.removeAll();
            desktop.repaint();
            desktop.add(frame);
            frame.setVisible(true);

        }

    }

    // displays collection on panel
    private class ViewCollectionAction extends AbstractAction {

        // EFFECTS: instantiates action with given name
        ViewCollectionAction() {
            super("View Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            ViewCollection view = new ViewCollection(collection, ResearchCollectionGUI.this);
            JInternalFrame collectionFrame = new JInternalFrame("My Collection", false, true, false, false);
            collectionFrame.getContentPane().setLayout(new BorderLayout());
            collectionFrame.getContentPane().add(view, BorderLayout.CENTER);
            collectionFrame.setVisible(true);
            collectionFrame.setSize(700, 500);
            collectionFrame.setLocation(50, 50);

            desktop.removeAll();
            desktop.repaint();
            desktop.add(collectionFrame);
            desktop.revalidate();

        }
    }

    // initiates the filter method in gui
    private class SearchCollectionAction extends AbstractAction {

        // EFFECTS: instantiates action with given name
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
                SearchCollection filtered = new SearchCollection(collection.filterCollection(searchLoc),
                        ResearchCollectionGUI.this);
                JInternalFrame searchFrame = new JInternalFrame("Search Results", false, true, false, false);
                searchFrame.getContentPane().setLayout(new BorderLayout());
                searchFrame.getContentPane().add(filtered, BorderLayout.CENTER);
                searchFrame.setVisible(true);
                searchFrame.setSize(700, 500);
                searchFrame.setLocation(50, 50);

                desktop.removeAll();
                desktop.repaint();
                desktop.add(searchFrame);
                desktop.revalidate();

            }
        }
    }

    // saves collection to data base
    private class SaveCollectionAction extends AbstractAction {

        // EFFECTS: instantiates action with given name
        SaveCollectionAction() {
            super("Save Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            if (collection == null) {
                JOptionPane.showMessageDialog(ResearchCollectionGUI.this, "No collection to save.",
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                jsonWriter.open();
                jsonWriter.write(collection);
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

    // loads collection from database and displays it on panel
    private class LoadCollectionAction extends AbstractAction {

        // EFFECTS: instantiates action with given name
        LoadCollectionAction() {
            super("Load Collection");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                collection = jsonReader.read();
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
