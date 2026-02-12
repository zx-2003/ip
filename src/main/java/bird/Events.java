package bird;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents an Event task.
 */
public class Events extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event task.
     *
     * @param description a brief explanation of the event.
     * @param from the date and time of the event given in yyyy-MM-dd HHmm format.
     * @param to the date and time of the event given in yyyy-MM-dd HHmm format.
     */
    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Provides a string for the event to be cached locally.
     *
     * @return the String format for the event that is saved in the storage.
     */
    public String toFileString() {
        return "E|" + (this.isDone? "1": "0") + "|" + this.description + "|" + this.from
                + "|" + this.to;
    }

    @Override
    public boolean isDueSoon() {
        LocalDate today = LocalDate.now();
        LocalDate deadlineDate = this.to.toLocalDate();

        long daysDifference = ChronoUnit.DAYS.between(today, deadlineDate);
        return daysDifference >= 0 && daysDifference <= 7;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
