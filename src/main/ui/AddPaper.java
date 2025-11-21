package ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import model.ResearchPaper;

//Credit: code written in this class (and private classes) is inspired by AlarmSystem:
// (https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git)

//creates a panel for papers details
public class AddPaper extends JInternalFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    private static final int LOC = 100;
    private static int sensorCount = 0;
    private ResearchPaper paper;
    private JTextField paperTitle;
    private JTextField paperAuthor;
    private JTextField paperDispline;
    private JTextField paperRating;
    private JTextField paperReadStatus;
    private String title;
    private String author;
    private String displine;
    private boolean readStatus;
    private int rating;
    private Component parent;

    // EFFECTS: instantiates a new paper details panel on the screen
    public AddPaper(ResearchPaper p, Component parent) {
        this.parent = parent;
        title = p.getPTitle();
        author = p.getAuthor();
        displine = p.getDisipline();
        rating = p.getOverallRating();
        readStatus = p.getReadStatus();
        paperTitle = new JTextField("Title:" + title);
        paperAuthor = new JTextField("Author:" + author);
        paperDispline = new JTextField("Displine:" + displine);
        paperRating = new JTextField("Rating:" + rating);
        paperReadStatus = new JTextField("Marked as Unread");
        paperTitle.setEditable(false);
        paperAuthor.setEditable(false);
        paperDispline.setEditable(false);
        paperRating.setEditable(true);
        paperReadStatus.setEditable(false);
        paperTitle.setAlignmentX(WIDTH / 2);
        display();
    }

    public void display() {

        JButton read = new JButton(new ReadAction());
        JButton unread = new JButton(new UnreadAction());
        read.setAlignmentX(WIDTH / 2);
        unread.setAlignmentX(WIDTH / 2);
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        cp.add(paperTitle);
        cp.add(paperAuthor);
        cp.add(paperDispline);
        cp.add(paperRating);
        cp.add(paperReadStatus);
        cp.add(read);
        cp.add(unread);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        sensorCount++;
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: sets position of new paper
    private void setPosition(Component parent) {
        setLocation(LOC * sensorCount, parent.getHeight() / 2 + LOC * sensorCount / 5);
    }

    // EFFECTS: allows you to mark a paper as read
    private class ReadAction extends AbstractAction {
        ReadAction() {
            super("Mark as Read");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (readStatus) {

                paperReadStatus.setText("Already marked as read");
            } else {
                readStatus = true;
                paperReadStatus.setText("Marked as read");
            }
        }
    }

    // EFFECTS: allows you to mark a paper as unread
    private class UnreadAction extends AbstractAction {
        UnreadAction() {
            super("Mark as Unread");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!readStatus) {

                paperReadStatus.setText("Already marked as unread");
            } else {
                readStatus = false;
                paperReadStatus.setText("Marked as unread");
            }
        }
    }

}
