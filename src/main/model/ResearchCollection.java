package model;

import java.util.ArrayList;
import java.util.List;

// do you need to add requires clauses for strings (or can you assume string wont be empty)?


// Represents a collection of research papers that has a title, filter,
// list of ResearchPaper 
public class ResearchCollection {
    private String cTitle;
    private ResearchPaper paper; 
    private String filter;
    private ArrayList<ResearchPaper> myPapers;

    
    //EFFECTS: constructs a collection with a title, no filter, and
    // an empty list of ResearchPaper
    public ResearchCollection(String cTitle){
        this.cTitle = cTitle;
        myPapers = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a reserach paper to collection, if its not already there
    public void addPaper(ResearchPaper paper){
        if (getResearchCollection().contains(paper)){
          //do nothing
        } else{
        myPapers.add(paper);
        }
    }

    //REQUIRES: filter to be a parameter of a ResearchPaper
    //(title, author, displine)
    //EFFECTS: filters ResearchCollection based on filter given 
    public ArrayList<ResearchPaper> filterCollection(String filter){
        ArrayList<ResearchPaper> filtered = new ArrayList<>();
        for(ResearchPaper paper : myPapers){
            if(filter == paper.getPTitle()){
                filtered.add(paper);

            } 
            if(filter == paper.getAuthor()){
                filtered.add(paper);
            }
            if(filter == paper.getDisipline()){
                filtered.add(paper);
            }
        }
            return filtered;

        }
       
    

    //create a for each loop with a boolean that goes through each paper 
    // checking for the filter
    // create a copy of the list (which reps the new filtered list) -> would this 
    // method then be considered trivial if it doesn't modify the actual list?

    public String getFilter(){
        return filter; 
    }

    public String getCTitle(){
        return cTitle;
    }

    public ArrayList<ResearchPaper> getResearchCollection(){
        return myPapers; 
    }


    

}
