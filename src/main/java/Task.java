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
        System.out.println("    Nice! I've marked this task as done:");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("    OK, I've marked this task as not done yet:");
    }

    @Override
    public String toString() {
        return "[" + this.statusIcon() + "] " + description;
    }
}
