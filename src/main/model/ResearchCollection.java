package model;

import java.util.ArrayList;

// do you need to add requires clauses for strings (or can you assume string wont be empty)?


// Represents a collection of research papers that has a title, filter,
// list of ResearchPaper 
public class ResearchCollection {
    
    //EFFECTS: constructs a collection with a title, no filter, and
    // an empty list of ResearchPaper
    public ResearchCollection(String title){
        // stub
    }

    //MODIFIES: this
    //EFFECTS: adds a reserach paper to collection 
    public void addPaper(ResearchPaper paper){
        //stub
    }

    //REQUIRES: filter to be a parameter of a ResearchPaper
    //(title, author, displine)
    //EFFECTS: filters ResearchCollection based on filter given 
    public void filterCollection(String filter){
        //stub
    }

    //create a for each loop with a boolean that goes through each paper 
    // checking for the filter
    // create a copy of the list (which reps the new filtered list) -> would this 
    // method then be considered trivial if it doesn't modify the actual list?

    public String getFilter(){
        return null; //stub 
    }

    public String getTitle(){
        return null; //stub
    }

    public ArrayList<ResearchPaper> getResearchCollection(){
        return null; //stub
    }


    

}
