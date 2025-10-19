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
public class TestJsonReader extends JsonTest{

    private ResearchCollection collection;
    private List<ResearchPaper> papers;


    @BeforeEach
    void runBefore(){
        ResearchCollection collection = new ResearchCollection();
        List<ResearchPaper> papers = new ArrayList<>();

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            collection = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expecting IOException
        }
    }

    @Test
    void testReaderEmptyResearchCollection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCollection.json");
        try {
            collection = reader.read();
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
            collection = reader.read();
            papers = collection.getResearchCollection();
            assertEquals(2, papers.size());
            checkPaper("title1", "miya", "math", true, 6, papers.get(0));
            checkPaper("title2", "daph", "econ", false, 3, papers.get(1));
            //expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
