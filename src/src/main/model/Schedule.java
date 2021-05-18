package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.*;

/**
 * Represents a list of workouts sorted by TrainingDay. For example, I can make a workout called "Chest Day
 * for TUESDAY and also add make a workout "Running"  for TUESDAY. These two workouts would be combined together for
 * tuesday. Learnt about EnumMap from https://www.baeldung.com/java-enum-map.
 */
public class Schedule implements Writable {
    protected EnumMap<TrainingDay, Workout> scheduleMap;
    protected JSONObject json;
    protected JSONArray jsonArray;


    public Schedule() {
        scheduleMap = new EnumMap<>(TrainingDay.class);
    }


    // REQUIRES: w!=null
    //MODIFIES: this
    //EFFECTS: adds workout to workouts in the schedule
    public void addWorkout(Workout w) {
        scheduleMap.put(w.getTrainingDay(), w);
    }

    // MODIFIES: this
    // EFFECTS: gets workout value from key td;
    public Workout getWorkout(TrainingDay td) {
        return scheduleMap.get(td);
    }


    // EFFECTS: returns all workouts in the schedule.
    public Collection<Workout> getAllWorkouts() {
        return scheduleMap.values();
    }

    public Set<Map.Entry<TrainingDay, Workout>> getSet() {
        return scheduleMap.entrySet();
    }


    // REQUIRES: td!=null
    //EFFECTS: returns workout that is in td.
    public String getWorkoutName(TrainingDay td) {
        return scheduleMap.get(td).getName();
    }

    // EFFECTS: given string w, finds workout in the schedule
    public Workout findWorkout(String w) {
        Workout found = null;
        for (Workout workout : scheduleMap.values()) {
            if (workout.getName() == w) {
                found = workout;
            }
        }
        return found;
    }

    @Override
    // EFFECTS: makes schedule into Json Object
    public JSONObject toJson() {
        json = new JSONObject();
        json.put("workout", workoutsToJson());
        return json;
    }

    // EFFECTS: returns things in this schedule as a JSON array
    public JSONArray workoutsToJson() {
        jsonArray = new JSONArray();

        for (Workout w : scheduleMap.values()) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }

}
