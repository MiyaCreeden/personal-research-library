package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.ResearchPaper;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@ExcludeFromJacocoGeneratedReport

public class JsonTest {
    protected void checkPaper(String title, String author, String displine, Boolean read, int rating, ResearchPaper paper) {
        assertEquals(title, paper.getPTitle());
        assertEquals(displine, paper.getDisipline());
        assertEquals(author, paper.getAuthor());
        assertEquals(read, paper.getReadStatus());
        assertEquals(rating, paper.getOverallRating());
    }
}


