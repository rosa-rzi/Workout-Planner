package model;

import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a workout with a name, a day and a list of exercises. Exercises can be added to the workout and each
 * workout is mapped based o TrainingDay.
 *
 */
public class Workout implements Writable {

    public String workoutName; // workout  name
    public TrainingDay trainingDay; // workout day
    public List<Exercise> listOfExercises; // current list of exercises in the workout


    // EFFECTS: constructs a workout with given workoutName, day and an empty exercise list.
    public Workout(String workoutName, TrainingDay trainingDay) {
        this.workoutName = workoutName;
        this.trainingDay = trainingDay;
        this.listOfExercises = new ArrayList<>();
    }


    // Getters (no documentation required)
    public String getName() {
        return workoutName;
    }

    public TrainingDay getTrainingDay() {
        return trainingDay;
    }

    public List<Exercise> getListOfExercises() {
        return this.listOfExercises;
    }


    // REQUIRES: exercise!=null
    // EFFECTS: add exercise to list of exercises
    public void addExercise(Exercise exercise) {
        listOfExercises.add(exercise);
    }


    // EFFECTS: returns names of exercises in listOfExercises
    public String getNamesOfExercises() {
        List<String> names = new LinkedList<>();
        for (Exercise e: listOfExercises) {
            String name = e.getName();
            names.add(name);
        }

        return String.valueOf(names);
    }

    @Override
    // Generated ny intelliJ
    //EFFECTS: compares whether two workouts are the same workout.
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workout workout = (Workout) o;
        return workoutName.equals(workout.workoutName) && trainingDay == workout.trainingDay
                && Objects.equals(listOfExercises, workout.listOfExercises);
    }

    @Override
    // Generated by intelliJ
    public int hashCode() {
        return Objects.hash(workoutName, trainingDay, listOfExercises);
    }

    @Override
    // EFFECTS: makes workout into Json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", workoutName);
        json.put("training day", trainingDay);
        json.put("exercises", listOfExercises);
        return json;
    }

}




