package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.ResearchCollection;
import model.ResearchPaper;

//Credit: code in this class was inspired by ListDialog and ListDemo
// in Oracle (https://docs.oracle.com/javase/tutorial/uiswing/components/list.html)

//creates a panel to display research collection
public class ViewCollection extends JPanel {
    private JList<String> paperList;
    private ResearchCollection collection;
    private Component parent;

    // EFFECTS: instantiates a JList of research papers (the research collection)
    public ViewCollection(ResearchCollection collection, Component parent) {
        this.collection = collection;
        this.parent = parent;

        setLayout(getLayout());
        setPreferredSize(new Dimension(700, 500));

        List<ResearchPaper> papers = collection.getResearchCollection();
        String[] paperTitles = new String[papers.size()];
        int i = 0;
        for (ResearchPaper p : papers) {
            paperTitles[i] = p.getPTitle();
            i++;
        }

        paperList = new JList<>(paperTitles);
        paperList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        paperList.setLayoutOrientation(JList.VERTICAL_WRAP);

        JScrollPane scroll = new JScrollPane(paperList);
        scroll.setPreferredSize(new Dimension(600, 400));
        add(scroll, BorderLayout.CENTER);

        JButton viewButton = new JButton("View Paper Details");
        viewButton.addActionListener(e -> viewSelectedPaper());
        add(viewButton, BorderLayout.SOUTH);

    }

    // EFFECTS: opens panel to display selected papers details
    private void viewSelectedPaper() {
        int index = paperList.getSelectedIndex();
        if (index >= 0) {
            ResearchPaper paper = collection.getResearchCollection().get(index);

            AddPaper paperInfo = new AddPaper(paper, parent);

            JFrame frame = (JFrame) parent;
            ResearchCollectionGUI gui = (ResearchCollectionGUI) frame;
            JDesktopPane desktop = gui.getDesktop();

            JInternalFrame content = new JInternalFrame(paper.getPTitle(), false, true, false, false);

            content.getContentPane().setLayout(new BorderLayout());
            content.getContentPane().add(paperInfo, BorderLayout.CENTER);

            content.setSize(300, 400);
            content.setVisible(true);

            desktop.add(content);
            desktop.revalidate();
            desktop.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a paper first.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
