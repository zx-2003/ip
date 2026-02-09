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

        try {
            Parser parser = new Parser(trimmed);

            if (parser.checkListCommand()) {
                return tasklist.getTaskListString();
            }

            else if (parser.checkFindCommand()) {
                return tasklist.findMatchingTask(trimmed);
            }

            else if (parser.checkMarkCommand()) {
                String[] segments = parser.input.split(" ");
                int index = Integer.parseInt(segments[1]) - 1;
                String msg = tasklist.markTaskAsDone(index);
                storage.saveTasks(tasklist.tasks);
                return msg;
            }

            else if (parser.checkUnmarkCommand()) {
                String[] segments = parser.input.split(" ");
                int index = Integer.parseInt(segments[1]) - 1;
                String msg = tasklist.markTaskAsUndone(index);
                storage.saveTasks(tasklist.tasks);
                return msg;
            }

            else if (parser.checkDeleteCommand()) {
                String msg = tasklist.deleteTask(parser.input);
                storage.saveTasks(tasklist.tasks);
                return msg;
            }

            else if (parser.checkToDoTask()) {
                String msg = tasklist.addToDoTask(parser.input);
                storage.saveTasks(tasklist.tasks);
                return msg;
            }

            else if (parser.checkEventTask()) {
                String msg = tasklist.addEventTask(parser.input);
                storage.saveTasks(tasklist.tasks);
                return msg;
            }

            else if (parser.checkDeadlineTask()) {
                String msg = tasklist.addDeadlineTask(parser.input);
                storage.saveTasks(tasklist.tasks);
                return msg;
            }

        } catch (Exception e) {
            return e.getMessage();
        }

        return "Sorry I don't get that";
    }
}
