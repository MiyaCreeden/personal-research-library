package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.ResearchCollection;
import model.ResearchPaper;


//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@ExcludeFromJacocoGeneratedReport
public class TestJsonWriter extends JsonTest{

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
            collection.addPaper(new ResearchPaper("title1", "miya", "math"));
            collection.addPaper(new ResearchPaper("title2", "daph", "econ"));
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

}
