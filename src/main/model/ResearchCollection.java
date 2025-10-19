package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistance.Writable;

// Represents a collection of research papers that has a title, filter,
// list of ResearchPaper 
public class ResearchCollection implements Writable {
    private ArrayList<ResearchPaper> myPapers;

    // EFFECTS: constructs a collection with no filter and
    // an empty list of ResearchPaper
    public ResearchCollection() {
        myPapers = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a reserach paper to collection, if its not already there
    public void addPaper(ResearchPaper paper) {
        if (getResearchCollection().contains(paper)) {
            // do nothing
        } else {
            myPapers.add(paper);
        }
    }

    // EFFECTS: constructs a new list of only the papers in the collection
    // that have been read
    public ArrayList<ResearchPaper> readCollection() {
        ArrayList<ResearchPaper> read = new ArrayList<>();
        for (ResearchPaper paper : myPapers) {
            if (paper.getReadStatus()) {
                read.add(paper);
            }
        }

        return read;
    }

    // EFFECTS: constructs a new list of only the papers in the collection
    // that are unread
    public ArrayList<ResearchPaper> unreadCollection() {
        ArrayList<ResearchPaper> unread = new ArrayList<>();
        for (ResearchPaper paper : myPapers) {
            if (!(paper.getReadStatus())) {
                unread.add(paper);
            }
        }

        return unread;
    }

    // REQUIRES: filter to be a string parameter of a ResearchPaper
    // (title, author, displine)
    // EFFECTS: filters ResearchCollection based on filter given
    public ArrayList<ResearchPaper> filterCollection(String filter) {
        ArrayList<ResearchPaper> filtered = new ArrayList<>();
        for (ResearchPaper paper : myPapers) {
            if (paper.getPTitle().equals(filter)) {
                filtered.add(paper);

            }

            if (paper.getAuthor().equals(filter)) {
                filtered.add(paper);
            }
            if (paper.getDisipline().equals(filter)) {
                filtered.add(paper);
            }

        }
        return filtered;

    }

    public ArrayList<ResearchPaper> getResearchCollection() {
        return myPapers;
    }

    //Credit: code below is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("My papers", papersToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray papersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ResearchPaper p : myPapers) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

}
