package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {
    private Schedule scheduleTest;

    private Workout w1 = new Workout("HITT", TrainingDay.MONDAY);
    private Workout w2 = new Workout("Legs", TrainingDay.TUESDAY);
    private Workout w3 = new Workout("Chest", TrainingDay.WEDNESDAY);
    private Workout w4 = new Workout("Push", TrainingDay.THURSDAY);
    private Workout w5 = new Workout("Upper", TrainingDay.FRIDAY);
    private Workout w6 = new Workout("Running", TrainingDay.SATURDAY);
    private Workout w7 = new Workout("BicepsTriceps", TrainingDay.SUNDAY);

    @BeforeEach
    public void setup() {
        scheduleTest = new Schedule();
    }

    @Test
    public void addMultipleWorkoutsTest() {
        scheduleTest.addWorkout(w1);
        scheduleTest.addWorkout(w2);
        scheduleTest.addWorkout(w3);
        assertEquals(3, scheduleTest.getAllWorkouts().size());
    }


    @Test
    void getWorkoutTest() {
        scheduleTest.addWorkout(w3);
        scheduleTest.addWorkout(w2);
        assertEquals("Legs", scheduleTest.getWorkoutName(TrainingDay.TUESDAY));


    }

    @Test
    void addWorkoutTest() {
        scheduleTest.addWorkout(w1);
        assertTrue(scheduleTest.getAllWorkouts().contains(w1));
    }

    @Test
    void getWorkoutName() {
        scheduleTest.addWorkout(w1);
        assertEquals("HITT", scheduleTest.getWorkoutName(TrainingDay.MONDAY));
    }


}
