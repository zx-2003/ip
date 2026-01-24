package bird;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    // testing the creation for a new todo object with valid parameters
    public void toDoTest1() {
        ToDos todo = new ToDos("todo 123");
        assertEquals("[T][ ] todo 123", todo.toString());
    }
}
