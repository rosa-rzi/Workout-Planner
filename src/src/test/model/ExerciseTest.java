package model;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExerciseTest {
    private Exercise exerciseTest;


    @BeforeEach
    public void setup() {
        exerciseTest = new Exercise("Kettle bell swings", 3,12);
    }

    @Test
    public void testConstructor (){
        assertEquals("Kettle bell swings", exerciseTest.getName());
        assertEquals(3, exerciseTest.getSet());
        assertEquals(12, exerciseTest.getRep());

    }

}