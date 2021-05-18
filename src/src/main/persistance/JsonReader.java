package persistance;

import model.Schedule;
import model.TrainingDay;
import model.Workout;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Represents a reader that reads schedule from JSON data stored in file.
// Code taken from JsReader from JsonSerilatizaationDemo.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads schedule from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Schedule read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSchedule(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Schedule parseSchedule(JSONObject jsonObject) {
        Schedule s = new Schedule();
        addWorkouts(s, jsonObject);
        return s;
    }

    // MODIFIES: s
    // EFFECTS: parses workouts from JSON object and adds them to schedule
    private void addWorkouts(Schedule s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            addWorkout(s, nextWorkout);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses workout from JSON object and adds it to workroom
    private void addWorkout(Schedule s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        TrainingDay trainingDay = TrainingDay.valueOf(jsonObject.getString("training day"));
        Workout workout = new Workout(name, trainingDay);
        s.addWorkout(workout);
    }
}
