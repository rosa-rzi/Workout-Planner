package persistanceTest;

import model.Schedule;
import model.TrainingDay;
import model.Workout;
import org.junit.jupiter.api.Test;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriteTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Schedule s = new Schedule();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Schedule s = new Schedule();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySchedule.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptySchedule.json");
            s = reader.read();
            assertEquals(0, s.getAllWorkouts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Schedule s = new Schedule();
            s.addWorkout(new Workout("HIIT", TrainingDay.TUESDAY));
            s.addWorkout(new Workout("legs", TrainingDay.THURSDAY));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSchedule.json");
            writer.open();
            writer.write(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralSchedule.json");
            s = reader.read();
            Collection<Workout> workouts = s.getAllWorkouts();
            assertEquals(2, workouts.size());
            checkWorkout("HIIT", TrainingDay.TUESDAY, s.getWorkout(TrainingDay.TUESDAY));
            checkWorkout("legs", TrainingDay.THURSDAY, s.getWorkout(TrainingDay.THURSDAY));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
