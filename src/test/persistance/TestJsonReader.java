package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.ResearchCollection;
import model.ResearchPaper;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@ExcludeFromJacocoGeneratedReport
public class TestJsonReader extends JsonTest {

    private ResearchCollection collection;
    private List<ResearchPaper> papers;
    private ResearchPaper testpaper1;
    private ResearchPaper testpaper2;



    @BeforeEach
    void runBefore() {
        
        collection = new ResearchCollection();
        List<ResearchPaper> papers = new ArrayList<>();
        testpaper1 = new ResearchPaper("title1", "miya", "math");
        testpaper2 = new ResearchPaper("title2", "daph", "econ");
        testpaper1.markAsRead();
        testpaper1.overallRating(3, 3);
        testpaper2.overallRating(0, 3);


    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            
            ResearchCollection collection = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expecting IOException
        }
    }

    @Test
    void testReaderEmptyResearchCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyResearchCollection.json");
        
        try {
            
            ResearchCollection collection = reader.read();
            assertEquals(0, collection.getResearchCollection().size());
            //expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralResearchCollection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralResearchCollection.json");
        
        try {
            
            ResearchCollection collection = reader.read();
            papers = collection.getResearchCollection();
            
            assertEquals(2, papers.size());
            
            checkPaper("title1", "miya", "math", true, 6, papers.get(0));
            checkPaper("title2", "daph", "econ", false, 3, papers.get(1));
            //expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        
    }

    @Test
    void testReaderGeneralCollectionNoUserMods() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCollectionNoUserMods.json");
        
        try {
            
            ResearchCollection collection = reader.read();
            papers = collection.getResearchCollection();
            
            assertEquals(1, papers.size());
            
            checkPaper("title3", "jim", "bio", false, 0, papers.get(0));
            
            //expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



    // why is collection = reader.read() not compiling correctly?
    
}
