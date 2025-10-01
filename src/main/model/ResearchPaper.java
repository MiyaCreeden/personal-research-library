package model;

// Represents an academic paper with a title,
// displine, an overall rating, an author
public class ResearchPaper {
    
    //EFFECTS: constructs a paper with a title, author,
    // overall rating =0
    public ResearchPaper() {
       //stub
    }

    //REQUIRES: 5 > enjoy and usefull > 0
    //MODIFIES: this
    //EFFECTS: assigns an overall rating to a paper based off
    // enjoyment and usefullness of the paper 
    public void overallRating(int enjoy, int usefull){
        //stub
    }

    public String getTitle(){
        return null;//stub
    }

    public String getAuthor(){
        return null; //stub
    }

    public int getOverallRating(){
        return 0; //stub
    }

    public String getDisipline(){
        return null; //stub
    }
}
