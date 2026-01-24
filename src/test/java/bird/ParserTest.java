package bird;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    // inserting todo task with no argument, returns an error
    public void parserTest1() {
        Parser parser = new Parser("todo");
        assertThrows(BirdException.class, () -> parser.isToDoTask());
    }
}
