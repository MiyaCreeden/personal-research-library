package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestReserchCollection {

    private ResearchCollection testCollection;
    private ArrayList<ResearchPaper> testFilteredCollection;
    private ArrayList<ResearchPaper> testReadCollection;
    private ArrayList<ResearchPaper> testUnreadCollection;
    private ResearchPaper testPaperA;
    private ResearchPaper testPaperB;
    private ResearchPaper testPaperC;

    @BeforeEach
    void runBefore() {
        testCollection = new ResearchCollection();
        testPaperA = new ResearchPaper("Immunology", "Heather Heath", "Biology");
        testPaperB = new ResearchPaper("Physical Chemistry", "Heather Heath", "Chemistry");
        testPaperC = new ResearchPaper("Neuroscience", "Tim Reed", "Biology");
        testFilteredCollection = new ArrayList<>();
        testReadCollection = new ArrayList<>();
        testUnreadCollection = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCollection.getResearchCollection().size());
    }

    @Test
    void testAddPaperSingle() {
        testCollection.addPaper(testPaperA);
        assertEquals(1, testCollection.getResearchCollection().size());
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
    }

    @Test
    void testAddPaperMany() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        assertEquals(2, testCollection.getResearchCollection().size());
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
        assertTrue(paperEquals(testPaperB, testCollection.getResearchCollection().get(1)));

    }

    @Test
    void testAddPaperDouble() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperA);
        assertEquals(1, testCollection.getResearchCollection().size());
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
    }

    @Test
    void testFilterCollectionNoMatch() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testFilteredCollection = testCollection.filterCollection("Math");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(0, testFilteredCollection.size());

    }

    @Test
    void testFilterCollectionOneMatch() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testFilteredCollection = testCollection.filterCollection("Tim Reed");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(1, testFilteredCollection.size());
        assertTrue(paperEquals(testPaperC, testFilteredCollection.get(0)));

    }

    @Test
    void testFilterCollectionManyMatch() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testFilteredCollection = testCollection.filterCollection("Heather Heath");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(2, testFilteredCollection.size());
        assertTrue(paperEquals(testPaperA, testFilteredCollection.get(0)));
        assertTrue(paperEquals(testPaperB, testFilteredCollection.get(1)));

    }

    @Test
    void testFilterCollectionManyMatchDiffFilter() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testFilteredCollection = testCollection.filterCollection("Biology");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(2, testFilteredCollection.size());
        assertTrue(paperEquals(testPaperA, testFilteredCollection.get(0)));
        assertTrue(paperEquals(testPaperC, testFilteredCollection.get(1)));

    }

    @Test
    void testFilterCollectionForTitleMatch() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testFilteredCollection = testCollection.filterCollection("Physical Chemistry");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(1, testFilteredCollection.size());
        assertTrue(paperEquals(testPaperB, testFilteredCollection.get(0)));

    }

    @Test
    void testFilterCollectionForTitleNoMatch() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testFilteredCollection = testCollection.filterCollection("Macbeth Research");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(0, testFilteredCollection.size());

    }

    @Test
    void testReadCollectionNone() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testReadCollection = testCollection.readCollection();
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(0, testReadCollection.size());
    }

    @Test
    void testReadCollectionSingle() {
        testPaperB.markAsRead();
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testReadCollection = testCollection.readCollection();
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(1, testReadCollection.size());
        assertTrue(paperEquals(testPaperB, testReadCollection.get(0)));

    }

    @Test
    void testReadCollectionMany() {
        testPaperB.markAsRead();
        testPaperC.markAsRead();
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testReadCollection = testCollection.readCollection();
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(2, testReadCollection.size());
        assertTrue(paperEquals(testPaperB, testReadCollection.get(0)));
        assertTrue(paperEquals(testPaperC, testReadCollection.get(1)));

    }

    @Test
    void testUnreadCollectionNone() {
        testPaperA.markAsRead();
        testPaperB.markAsRead();
        testPaperC.markAsRead();
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testUnreadCollection = testCollection.unreadCollection();
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(0, testUnreadCollection.size());

    }

    @Test
    void testUnreadCollectionSingle() {
        testPaperB.markAsRead();
        testPaperC.markAsRead();
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testUnreadCollection = testCollection.unreadCollection();
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(1, testUnreadCollection.size());
        assertTrue(paperEquals(testPaperA, testUnreadCollection.get(0)));

    }

    @Test
    void testUnreadCollectionMany() {
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testUnreadCollection = testCollection.unreadCollection();
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(3, testUnreadCollection.size());
        assertTrue(paperEquals(testPaperA, testUnreadCollection.get(0)));
        assertTrue(paperEquals(testPaperB, testUnreadCollection.get(1)));
        assertTrue(paperEquals(testPaperC, testUnreadCollection.get(2)));

    }

    // EFFECTS: helper method for testing addPaper method
    public boolean paperEquals(ResearchPaper cpaper, ResearchPaper paper) {
        if (cpaper == paper) {
            return true;
        } else {
            return false;
        }
    }

}
