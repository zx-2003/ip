package bird;

import java.util.ArrayList;

public class BirdBot {
    private final Storage storage;
    private final TaskList tasklist;

    public BirdBot() {
        this.storage = new Storage("./data/bird.txt");
        ArrayList<Task> tasks = storage.load();
        TaskList taskList = new TaskList(tasks);
        this.tasklist = taskList;
    }

    public String getWelcomeMessage() {
        return "Hello this is bird.";
    }

    public String getResponse(String input) {
        if (input == null) {
            return "please type something in";
        }

        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            return "please type something";
        }

        return "Bird heard" + trimmed;
    }
}
