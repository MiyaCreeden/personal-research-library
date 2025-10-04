package model;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class TestReserchCollection {
    private ResearchCollection testCollection;
    private List<ResearchPaper> testCollectionCopy;
    private ResearchPaper testPaperA;
    private ResearchPaper testPaperB;
    private ResearchPaper testPaperC;


    @BeforeEach
    void runBefore(){
        testCollection = new ResearchCollection("All Papers");
        testPaperA = new ResearchPaper("Immunology", "Heather Heath", "Biology");
        testPaperB = new ResearchPaper("Physical Chemistry", "Heather Heath", "Chemistry");
        testPaperC = new ResearchPaper("Neuroscience", "Tim Reed", "Biology");
        testCollectionCopy = new ArrayList<>();
    }

    @Test
    void testConstructor(){
        assertEquals("All Papers", testCollection.getTitle());
        assertEquals(0, testCollection.getResearchCollection().size()); 
    }

    @Test
    void testAddPaperSingle(){
        testCollection.addPaper(testPaperA);
        assertEquals(1, testCollection.getResearchCollection().size());
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
    }

    @Test
    void testAddPaperMany(){
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        assertEquals(2, testCollection.getResearchCollection().size());
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
        assertTrue(paperEquals(testPaperB, testCollection.getResearchCollection().get(1)));

    }

    @Test
    void testAddPaperDouble(){
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperA);
        assertEquals(1, testCollection.getResearchCollection().size());
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
    }

    @Test
    void testFilterCollectionNoMatch(){
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testCollection.filterCollection("Math");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(0, testCollectionCopy.size());   

    }

    @Test
    void testFilterCollectionOneMatch(){
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testCollection.filterCollection("Tim Reed");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(1, testCollectionCopy.size()); 
        assertTrue(paperEquals(testPaperC, testCollection.getResearchCollection().get(0)));

    }

     @Test
    void testFilterCollectionManyMatch(){
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testCollection.filterCollection("Heather Heath");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(2, testCollectionCopy.size()); 
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
        assertTrue(paperEquals(testPaperB, testCollection.getResearchCollection().get(1)));

    }

    @Test
    void testFilterCollectionManyMatchDiffFilter(){
        testCollection.addPaper(testPaperA);
        testCollection.addPaper(testPaperB);
        testCollection.addPaper(testPaperC);
        testCollection.filterCollection("Biology");
        assertEquals(3, testCollection.getResearchCollection().size());
        assertEquals(2, testCollectionCopy.size()); 
        assertTrue(paperEquals(testPaperA, testCollection.getResearchCollection().get(0)));
        assertTrue(paperEquals(testPaperC, testCollection.getResearchCollection().get(1)));

    }

    

    
    
    //EFFECTS: helper method for testing addPaper method
    public boolean paperEquals(ResearchPaper paper, ResearchPaper cpaper){
        return false; //stub
        }
///     if (paper == this.paper){
///         return true;
///     }else {return false}

/// helper method for test

    

    
   



}
