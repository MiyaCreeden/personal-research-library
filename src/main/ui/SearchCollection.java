package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.ResearchPaper;

//Credit: code in this class was inspired by ListDialog and ListDemo
// in Oracle (https://docs.oracle.com/javase/tutorial/uiswing/components/list.html)

//creates the filtered panel with search results
public class SearchCollection extends JPanel {
    private JList<String> searchedList;
    private ArrayList<ResearchPaper> results;
    private Component parent;

    // EFFECTS: instantiates a new list of only search results
    public SearchCollection(ArrayList<ResearchPaper> filtered, Component parent) {
        this.results = filtered;
        this.parent = parent;

        setLayout(getLayout());
        setPreferredSize(new Dimension(700, 500));

        String[] paperTitles = new String[results.size()];
        int i = 0;
        for (ResearchPaper p : results) {
            paperTitles[i] = p.getPTitle();
            i++;
        }

        searchedList = new JList<>(paperTitles);
        searchedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        searchedList.setLayoutOrientation(JList.VERTICAL_WRAP);

        JScrollPane scroll = new JScrollPane(searchedList);
        scroll.setPreferredSize(new Dimension(600, 400));
        add(scroll, BorderLayout.CENTER);

    }

}
