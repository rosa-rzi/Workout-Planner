package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class WorkoutTest {
    private Workout workoutTest;

    private Exercise E1 = new Exercise("Band walks", 4, 15);
    private Exercise E2 = new Exercise("Bridge pulse", 4, 20);
    private Exercise E3 = new Exercise("Squats", 3, 10);
    private Exercise E4 = new Exercise("American deadlift", 3, 8);

    @BeforeEach
    public void setup() {
        workoutTest = new Workout("KillerLegs", TrainingDay.TUESDAY);
    }

    @Test
    public void testConstructor() {
        assertEquals("KillerLegs", workoutTest.getName());
        assertEquals(TrainingDay.TUESDAY, workoutTest.getTrainingDay());
    }


    @Test
    public void getListOfExercisesTest() {
        assertFalse(workoutTest.getListOfExercises().contains(E1));
        workoutTest.addExercise(E1);
        assertTrue(workoutTest.getListOfExercises().contains(E1));
        workoutTest.addExercise(E2);
        assertTrue(workoutTest.getListOfExercises().contains(E2));
    }

    @Test
    public void getExerciseNamesTest() {
        workoutTest.addExercise(E3);
        workoutTest.addExercise(E4);
        assertTrue(workoutTest.getNamesOfExercises().contains("American deadlift"));
        assertFalse(workoutTest.getNamesOfExercises().contains("Bridge pulse"));
    }

    @Test
    // hashcode() unit test from https://stackoverflow.com/questions/4449728/how-can-i-do-unit-test-for-hashcode
    public void testEquals_Symmetric() {
        Workout w1 = new Workout("Legs", TrainingDay.TUESDAY);
        Workout w2 = new Workout("Legs", TrainingDay.TUESDAY);
        Workout w3 = new Workout("Arms", TrainingDay.FRIDAY);
        assertTrue(w1.equals(w2) && w1.equals(w1));
        assertTrue(w1.hashCode() == w2.hashCode());
        assertFalse(w1.equals(w3) && w3.equals(w1));
        assertFalse(w1.hashCode() == w3.hashCode());
    }
}
