/*package ui;

import model.Exercise;
import model.Schedule;
import model.TrainingDay;
import model.Workout;

import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WorkoutPlanner {
    private static final String JSON_STORE = "./data/schedule.json";
    private Schedule schedule;
    private Scanner input;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs plan and runs the workout planner app
    public WorkoutPlanner() throws FileNotFoundException {
        schedule = new Schedule();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        makeWorkoutPlanner();

    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void makeWorkoutPlanner() {
        boolean continuePlanning = true;
        String command = null;

        init(); // idk

        while (continuePlanning) {
            displayOptions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                continuePlanning = false;
            } else {
                processCommands(command);
            }
        }
        System.out.println("\nThank you for using fitness planner!");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommands(String command) {
        if (command.equals("w")) {
            createWorkout();
        } else if (command.equals("e")) {
            createExercise();
        } else if (command.equals("v")) {
            viewSchedule();
        } else if (command.equals("g")) {
            getExercises();
        } else if (command.equals("s")) {
            savePlan();
        } else if (command.equals("l")) {
            loadPlan();
        } else {
            System.out.println("Selection is not valid");
        }

    }

    // MODIFIES: this
    // EFFECTS: processes users command for picking training day
    public TrainingDay processDayCommand(String command) {
        TrainingDay td;
        if (command.equals("1")) {
            td = TrainingDay.SUNDAY;
        } else if (command.equals("2")) {
            td = TrainingDay.MONDAY;
        } else if (command.equals("3")) {
            td = TrainingDay.TUESDAY;
        } else if (command.equals("4")) {
            td = TrainingDay.WEDNESDAY;
        } else if (command.equals("5")) {
            td = TrainingDay.THURSDAY;
        } else if (command.equals("6")) {
            td = TrainingDay.FRIDAY;
        } else if (command.equals("7")) {
            td = TrainingDay.SATURDAY;
        } else {
            System.out.print("Selection is not valid");
            td = null;
        }
        return td;
    }


    // MODIFIES: this
    // EFFECTS: initializes schedule
    private void init() {
        schedule = new Schedule();
        input = new Scanner(System.in);
        scanner = new Scanner(System.in);
    }

    // EFFECTS: displays option of creating workout or view schedule
    private void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> Create new workout");
        System.out.println("\te -> Add new exercise to a workout");
        System.out.println("\tg -> Get list of exercises in a workout");
        System.out.println("\tv -> View workouts for the week");
        System.out.println("\ts -> save plan to file");
        System.out.println("\tl -> load plan from file");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: creates a workout
    private void createWorkout() {
        System.out.println("Enter name for workout");
        String name = input.next();
        System.out.println("\nSelect a day for your workout:");
        System.out.println("\t1 -> Sunday");
        System.out.println("\t2 -> Monday");
        System.out.println("\t3 -> Tuesday");
        System.out.println("\t4 -> Wednesday");
        System.out.println("\t5 -> thursday");
        System.out.println("\t6 -> Friday");
        System.out.println("\t7 -> Saturday");
        String command = input.next();
        TrainingDay trainingDay = processDayCommand(command);
        Workout workout = new Workout(name, trainingDay);
        schedule.addWorkout(workout);
        System.out.println("You've selected " + trainingDay + " for " + workout.getName());
    }

    //MODIFIES: this
    // EFFECTS: creates an exercise
    private void createExercise() {
        System.out.println("Enter name for exercise");
        String name = input.next();
        System.out.println("Enter number of sets");
        int sets = scanner.nextInt();
        System.out.println("Enter number of repetitions");
        int reps = scanner.nextInt();
        Exercise exercise = new Exercise(name, sets, reps);
        System.out.println(name + ": " + sets + " sets of " + reps + " repetitions");
        System.out.println("Input workout name to add exercise to");
        String workoutName = scanner.next();
        Collection<Workout> allWorkouts = schedule.getAllWorkouts();
        for (Workout workout : allWorkouts) {
            if (workout.getName().equals(workoutName)) {
                workout.addExercise(exercise);
            }
        }
        System.out.println(exercise.getName() + " has been added to " + workoutName);
    }


    // MODIFIES: this
    // EFFECTS: given a workout name, returns names of exercises in workout
    private void getExercises() {
        System.out.println("Enter name of the workout");
        String selected = input.next();

        List<String> names = new LinkedList<>();
        for (Workout workout : schedule.getAllWorkouts()) {
            if (workout.getName().equals(selected)) {
                names.add(workout.getNamesOfExercises());
            }
        }
        System.out.println(names);
    }


    // MODIFIES: this
    // EFFECTS: prints out list of workouts in schedule by TrainingDay
    private void viewSchedule() {
        System.out.println("Here is your workout plan for this week");
        schedule.getSet();

    }

    // EFFECTS: saves the plan to file
    private void savePlan() {
        try {
            jsonWriter.open();
            jsonWriter.write(schedule);
            jsonWriter.close();
            System.out.println("Saved " + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads plan from file
    private void loadPlan() {
        try {
            schedule = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}*/
