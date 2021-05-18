package persistanceTest;

import model.TrainingDay;
import model.Workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWorkout(String name, TrainingDay trainingDay, Workout workout) {
        assertEquals(name, workout.getName());
        assertEquals(trainingDay, workout.getTrainingDay());
    }
}