package persistance;

import org.json.JSONObject;

//Credit: code in this class is modeled from JsonSerilizationDemo
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
