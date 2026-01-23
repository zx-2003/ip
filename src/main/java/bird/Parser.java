package bird;

public class Parser {
    protected String input;

    public Parser(String input) {
        this.input = input;
    }

    public boolean isListCommand() {
        return input.equals("list");
    }

    public boolean isMarkCommand() {
        return input.startsWith("mark") || input.startsWith("unmark");
    }

    public boolean isByeCommand() {
        return input.equals("bye");
    }

    public boolean isDeleteCommand() throws BirdException {
        if (!input.startsWith("delete")) {
            return false;
        }

        String segments = input.substring(6).trim();
        if (segments.isEmpty()) {
            throw new BirdException("Error: you must choose to delete a task");
        }
        return true;
    }

    public boolean isDeadlineTask() throws BirdException {
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

    public boolean isEventTask() throws BirdException {
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

    public boolean isToDoTask() throws BirdException {
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
