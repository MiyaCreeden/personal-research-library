package persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.ResearchPaper;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

@ExcludeFromJacocoGeneratedReport

public class JsonTest {
    protected void checkPaper(String t, String a, String d, Boolean r, int rating, ResearchPaper p) {
        assertEquals(t, p.getPTitle());
        assertEquals(d, p.getDisipline());
        assertEquals(a, p.getAuthor());
        assertEquals(r, p.getReadStatus());
        assertEquals(rating, p.getOverallRating());
    }
}


