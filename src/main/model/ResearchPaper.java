package model;

// Represents an academic paper with a title,
// displine, an overall rating, an author, and a readStatus
public class ResearchPaper {
    private String ptitle;
    private String author;
    private String displine;
    private boolean readStatus;
    private int rating;

    // EFFECTS: constructs a paper with a title, author, displine,
    // overall rating =0, and a status of unread
    public ResearchPaper(String ptitle, String author, String displine) {
        this.ptitle = ptitle;
        this.author = author;
        this.displine = displine;
        this.rating = 0;
        this.readStatus = false;
    }

    // REQUIRES: 5 ≥ (enjoy and usefull) ≥ 0
    // MODIFIES: this
    // EFFECTS: assigns an overall rating to a paper based off
    // enjoyment and usefullness of the paper
    public void overallRating(int enjoy, int usefull) {
        this.rating = this.rating + enjoy + usefull;
    }

    // MODIFIES: this
    // EFFECTS: updates paper status to read
    public void markAsRead() {
        this.readStatus = true;
    }

    // MODIFIES: this
    // EFFECTS: updates paper status to unread
    public void markAsUnread() {
        this.readStatus = false;
    }

    public String getPTitle() {
        return ptitle;
    }

    public String getAuthor() {
        return author;
    }

    public int getOverallRating() {
        return rating;
    }

    public String getDisipline() {
        return displine;
    }

    public boolean getReadStatus() {
        return readStatus;
    }

}
