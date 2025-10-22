package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.ResearchCollection;
import model.ResearchPaper;


//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@ExcludeFromJacocoGeneratedReport
public class TestJsonWriter extends JsonTest{

    private ResearchPaper testpaper1;
    private ResearchPaper testpaper2;

    @BeforeEach
    void runBefore(){
        testpaper1 = new ResearchPaper("title1", "miya", "math");
        testpaper2 = new ResearchPaper("title2", "daph", "econ");
        //testpaper1.markAsRead();
        testpaper1.overallRating(3, 3);
        testpaper2.overallRating(0, 3);

    }

     @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyResearchCollection() {
        try {
            ResearchCollection collection = new ResearchCollection();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyResearchCollection.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyResearchCollection.json");
            collection = reader.read();
            assertEquals(0, collection.getResearchCollection().size());
            //expected
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralResearchCollection() {
        try {
            ResearchCollection collection = new ResearchCollection();
            testpaper1.markAsRead();
            collection.addPaper(testpaper1);
            collection.addPaper(testpaper2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralResearchCollection.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralResearchCollection.json");
            collection = reader.read();
            List<ResearchPaper> papers = collection.getResearchCollection();
            assertEquals(2, papers.size());
            checkPaper("title1", "miya", "math", true, 6, papers.get(0));
            checkPaper("title2", "daph", "econ", false, 3, papers.get(1));
            //expected

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCollectionNoUserMods() {
        try {
            ResearchCollection collection = new ResearchCollection();
            collection.addPaper(new ResearchPaper("title3", "jim", "bio"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCollectionNoUserMods.json");
            writer.open();
            writer.write(collection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCollectionNoUserMods.json");
            collection = reader.read();
            List<ResearchPaper> papers = collection.getResearchCollection();
            assertEquals(1, papers.size());
            checkPaper("title3", "jim", "bio", false, 0, papers.get(0));
            
            //expected

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
