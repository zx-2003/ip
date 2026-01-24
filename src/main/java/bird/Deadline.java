package bird;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime date;

    /**
     * Constructor for the deadline task.
     * @param description a brief description of the task.
     * @param date the date and time of the event given in yyyy-MM-dd HHmm format.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Provides a string for the deadline to be cached locally.
     * @return the String format for the deadline that is saved in the storage.
     */
    @Override
    public String toFileString() {
        return "D|" + (this.isDone? "1": "0") + "|" + this.description + "|" + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
