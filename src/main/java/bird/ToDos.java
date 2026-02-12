package bird;

/**
 * Represents a ToDo task
 */
public class ToDos extends Task {

    /**
     * Constructor for the ToDo task.
     *
     * @param description a brief description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public boolean isDueSoon() {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
