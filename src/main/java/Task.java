public class Task {
    protected boolean isDone = false;
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

    // new function so that we can write to bird.txt
    public String toFileString() {
        return "T|" + (this.isDone? "1" : "0") + "|" + this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[" + this.statusIcon() + "] " + description;
    }
}
