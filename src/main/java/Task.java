public class Task {
    boolean isDone = false;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String statusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.statusIcon() + "] " + description;
    }
}
