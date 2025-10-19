package persistance;

import java.io.IOException;

import org.json.JSONObject;

import model.ResearchCollection;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)


//Represents a reader that reads ResearchCollection from Json data stored in file
public class JsonReader {

    //EFFECTS: constructs a reader to read from the source file
    public JsonReader(){
        //stub
    }

    //EFFECTS: reads a ResearchCollection from file and returns it, throws 
    // IO Exception if it cant read from file
    public ResearchCollection read() throws IOException{
        return null; //stub
    }

    // EFFECTS: reads source file as string and returns it; 
    // throws IOException if error occurs
    private String readFile() throws IOException {
        return null;//stub
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ResearchCollection parseCollection() {
        return null; //stub
    }

    // MODIFIES: research collection
    // EFFECTS: parses papers from JSON object and adds them to research collection
    private void addPapers() {
        //stub
    }

    // MODIFIES: research collection
    // EFFECTS: parses paper from JSON object and adds it to research collection
    private void addThingy() {
        //stub
    }

}

