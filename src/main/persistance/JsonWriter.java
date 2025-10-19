package persistance;

import java.io.FileNotFoundException;

import model.ResearchCollection;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

public class JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of research collection to file
    public void write(ResearchCollection collection) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        //stub
    }

}
