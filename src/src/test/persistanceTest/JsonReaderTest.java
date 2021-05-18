package persistanceTest;

import model.Schedule;
import model.TrainingDay;
import model.Workout;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Schedule s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySchedule() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySchedule.json");
        try {
            Schedule s = reader.read();
            assertEquals(0, s.getAllWorkouts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSchedule.json");
        try {
            Schedule s = reader.read();
            Collection<Workout> workouts = s.getAllWorkouts();
            assertEquals(2, workouts.size());
            checkWorkout("HIIT", TrainingDay.TUESDAY, s.getWorkout(TrainingDay.TUESDAY));
            checkWorkout("legs", TrainingDay.THURSDAY, s.getWorkout(TrainingDay.THURSDAY));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}