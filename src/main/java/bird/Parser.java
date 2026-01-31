package bird;

public class Parser {
    protected String input;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * Checks if the input user gave is "list".
     *
     * @return true if the user inputs "list" into the parser.
     */
    public boolean checkListCommand() {
        return input.equals("list");
    }

    /**
     * Checks if the input user gave is "mark" or "unmark".
     *
     * @return true if the user inputs "mark" or "unmark" into the parser.
     */
    public boolean checkMarkCommand() {
        return input.startsWith("mark");
    }

    public boolean checkUnmarkCommand() {
        return input.startsWith("unmark");
    }

    /**
     * Checks if the input user gave is "bye".
     *
     * @return true if the user inputs "bye" into the parser.
     */
    public boolean checkByeCommand() {
        return input.equals("bye");
    }

    /**
     * Checks if the input user gave starts with "delete".
     *
     * @return true if user inputs "delete" into the parser.
     * @throws BirdException if user did not add a task to delete.
     */
    public boolean checkDeleteCommand() throws BirdException {
        if (!input.startsWith("delete")) {
            return false;
        }

        String segments = input.substring(6).trim();
        if (segments.isEmpty()) {
            throw new BirdException("Error: you must choose to delete a task");
        }
        return true;
    }

    /**
     * Checks if the input user gave starts with "find".
     *
     * @return true if user inputs "find" into the parser.
     * @throws BirdException if user did not add a task to find.
     */
    public boolean checkFindCommand() throws BirdException {
        if (!input.startsWith("find")) {
            return false;
        }

        String segments = input.substring(4).trim();
        if (segments.isEmpty()) {
            throw new BirdException("Error: you did not enter a task to find");
        }
        return true;
    }

    /**
     * Checks if the input user starts with "deadline" and provides a deadline task.
     *
     * @return true if user inputs "deadline" into the parser followed by a deadline task,
     * /b <date>yyyy-MM-dd HHmm</date>
     * @throws BirdException if user does not provide task or provides the task in the wrong format.
     */
    public boolean checkDeadlineTask() throws BirdException {
        if (!input.startsWith("deadline")) {
            return false;
        }

        String info = input.substring(8).trim();
        if (info.isEmpty()) {
            throw new BirdException("Error: deadline task must have a description");
        }

        String[] segments = info.split(" /by ");

        if (segments.length < 2) {
            throw new BirdException("Error: deadline must be in format:\n" +
                    "deadline <desc> /by yyyy-MM-dd HHmm");
        }
        return true;
    }

    /**
     * Checks if the input user starts with "event" and provides an event task.
     *
     * @return true if user inputs "event" into the parser followed by an event task,
     * /from <date>yyyy-MM-dd HHmm</date>, /to <date>yyyy-MM-dd HHmm</date>.
     * @throws BirdException if user does not provide task or provides the task in the wrong format.
     */
    public boolean checkEventTask() throws BirdException {
        if (!input.startsWith("event")) {
            return false;
        }

        String info = input.substring(5).trim();
        if (info.isEmpty()) {
            throw new BirdException("Error: event task must have a description");
        }
        String[] segments = info.split(" /from ");
        if (segments.length < 2) {
            throw new BirdException("Error: event must be in format:\n" +
                    "event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String[] timing = segments[1].split(" /to ");
        if (timing.length < 2) {
            throw new BirdException("Error: event must be in format:\n" +
                    "event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        return true;
    }

    /**
     * Checks if user inputs "todo" followed by a todo task.
     *
     * @return true if user inputs "todo" followed by a todo task.
     * @throws BirdException if user does not provide task.
     */
    public boolean checkToDoTask() throws BirdException {
        if (!input.startsWith("todo")) {
            return false;
        }

        String info = input.substring(4).trim();
        if (info.isEmpty()) {
            throw new BirdException("Error: todo task must have a description");
        }

        return true;
    }
}
