package persistance;

import org.json.JSONObject;

// Code taken from JsonSerializationDemo.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
