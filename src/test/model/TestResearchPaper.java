package model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestResearchPaper {
    private ResearchPaper testResearchPaper;
    
    @BeforeEach
    void runBefore() {
        testResearchPaper = new ResearchPaper("Low-Dimensional Topology", 
        "Bob Brown","Math");


    }

    @Test
    void testConstructor() {
        assertEquals("Low-Dimensional Topology", testResearchPaper.getTitle());
        assertEquals("Bob Brown", testResearchPaper.getAuthor());
        assertEquals("Math", testResearchPaper.getDisipline());
        assertEquals(0, testResearchPaper.getOverallRating());
        assertEquals("unread", testResearchPaper.getReadStatus());
    }

    @Test
    void testOverallRatingDoesntChange(){
        testResearchPaper.overallRating(0, 0);
        assertEquals(0, testResearchPaper.getOverallRating());
    }

    @Test
    void testOverallRatingOnlyEnjoy(){
        testResearchPaper.overallRating(3, 0);
        assertEquals(3, testResearchPaper.getOverallRating());
    }

    @Test
    void testOverallRatingOnlyUsefull(){
        testResearchPaper.overallRating(0, 5);
        assertEquals(5, testResearchPaper.getOverallRating());
    }

    @Test
    void testOverallRatingBoth(){
        testResearchPaper.overallRating(3, 4);
        assertEquals(7, testResearchPaper.getOverallRating());
    }

    @Test
    void testMarkAsReadSingle(){
        testResearchPaper.markAsRead();
        assertEquals("read", testResearchPaper.getReadStatus());
    }

    @Test
    void testMarkAsReadDouble(){
        testResearchPaper.markAsRead();
        testResearchPaper.markAsRead();
        assertEquals("read", testResearchPaper.getReadStatus());
    }

     @Test
    void testMarkAsReadInverse(){
        testResearchPaper.markAsRead();
        testResearchPaper.markAsUnread();
        assertEquals("unread", testResearchPaper.getReadStatus());
    }

    @Test
    void testMarkAsUnreadSingle(){
        testResearchPaper.markAsUnread();
        assertEquals("unread", testResearchPaper.getReadStatus());
    }

    @Test
    void testMarkAsUnreadDouble(){
        testResearchPaper.markAsUnread();
        testResearchPaper.markAsUnread();
        assertEquals("unread", testResearchPaper.getReadStatus());
    }

    @Test
    void testMarkAsUnreadInverse(){
        testResearchPaper.markAsUnread();
        testResearchPaper.markAsRead();
        assertEquals("unread", testResearchPaper.getReadStatus());
    }
}

