package model;

import java.util.ArrayList;


// Represents a collection of research papers that has a title, filter,
// list of ResearchPaper 
public class ResearchCollection {
    
    //EFFECTS: constructs a collection with a title,
    // an empty list of ResearchPaper
    public ResearchCollection(){
        // stub
    }

    //MODIFIES: this
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

    public ArrayList<ResearchPaper> getResearchPaper(){
        return null; //stub
    }

    

}
