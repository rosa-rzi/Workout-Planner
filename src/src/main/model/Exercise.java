package model;

/**
 * Represents an exercise with a name, set, reps and rest time (in minutes) between sets.
 */
public class Exercise {
    private String name; //name of exercise
    private int set; // number of sets for the exercise
    private int rep; // number of repetitions for each set


    // REQUIRES: non-empty name, set,rep and rest must be positive integers >=0
    // EFFECTS: name of exercise is set to exerciseName; number of sets is assigned to exerciseSet; number
    // of repetitions is assigned to exerciseReps; rest time is set as exerciseRest
    public Exercise(String name, int set, int rep) {
        this.name = name;
        this.set = set;
        this.rep = rep;

    }

    // GETTER METHODS (no documentation needed)
    public String getName() {
        return name;
    }

    public int getSet() {
        return set;
    }

    public int getRep() {
        return rep;
    }


}


