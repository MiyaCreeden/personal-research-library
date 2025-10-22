package persistance;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.ResearchCollection;
import model.ResearchPaper;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)


//Represents a reader that reads ResearchCollection from Json data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads a ResearchCollection from file and returns it, throws 
    // IO Exception if it cant read from file
    public ResearchCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCollection(jsonObject);
    }
    

    // EFFECTS: reads source file as string and returns it; 
    // throws IOException if error occurs
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ResearchCollection parseCollection(JSONObject object) {
        ResearchCollection collection = new ResearchCollection();
        addPapers(collection, object);
        return collection;
    }

    // MODIFIES: research collection
    // EFFECTS: parses papers from JSON object and adds them to research collection
    private void addPapers(ResearchCollection collection, JSONObject object) {
        JSONArray jsonArray = object.getJSONArray("My papers");
        for (Object json : jsonArray) {
            JSONObject nextPaper = (JSONObject) json;
            addPaper(collection, nextPaper);
        }
    }

    // MODIFIES: research collection
    // EFFECTS: parses paper from JSON object and adds it to research collection
    private void addPaper(ResearchCollection collection, JSONObject object) {
        String ptitle = object.getString("title");
        String author = object.getString("author");
        String displine = object.getString("displine");
        Boolean readStatus = object.getBoolean("read");
        int rating = object.getInt("rating");
        ResearchPaper paper = new ResearchPaper(ptitle, author, displine);
        paper.setRating(rating);
        paper.setReadStatus(readStatus);
        //when you instantiate a new paper its reseting the rating to 0 and readstatus to false
        collection.addPaper(paper);
    }

}

    



