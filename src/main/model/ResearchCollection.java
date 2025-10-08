package model;

import java.util.ArrayList;




// Represents a collection of research papers that has a title, filter,
// list of ResearchPaper 
public class ResearchCollection {
    private ResearchPaper paper; 
    private String filter;
    private ArrayList<ResearchPaper> myPapers;

    
    //EFFECTS: constructs a collection with no filter and
    // an empty list of ResearchPaper
    public ResearchCollection(){
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

    //EFFECTS: constructs a new list of only the papers in the collection
    // that have been read
    public ArrayList<ResearchPaper> readCollection(){
        ArrayList<ResearchPaper> read = new ArrayList<>();
        for(ResearchPaper paper : myPapers){
            if(paper.getReadStatus()){
                read.add(paper);
        }
    }
    
    return read;
}
            

        
    

    //EFFECTS: constructs a new list of only the papers in the collection
    // that are unread
    public ArrayList<ResearchPaper> unreadCollection(){
        ArrayList<ResearchPaper> unread = new ArrayList<>();
        for(ResearchPaper paper : myPapers){
            if(!(paper.getReadStatus())){
                unread.add(paper);
        }
    }
    
    return unread;
    }

    //REQUIRES: filter to be a string parameter of a ResearchPaper
    //(title, author, displine)
    //EFFECTS: filters ResearchCollection based on filter given 
    public ArrayList<ResearchPaper> filterCollection(String filter){
        ArrayList<ResearchPaper> filtered = new ArrayList<>();
        for(ResearchPaper paper : myPapers){
            if(paper.getPTitle().equals(filter)){
                filtered.add(paper);

            } 
            
            if(paper.getAuthor().equals(filter)){
                filtered.add(paper);
            }
            if(paper.getDisipline().equals(filter)){
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


    public ArrayList<ResearchPaper> getResearchCollection(){
        return myPapers; 
    }


    

}
