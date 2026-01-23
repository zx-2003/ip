import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toFileString() {
        return "E|" + (this.isDone? "1": "0") + "|" + this.description + "|" + this.from
                + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }
}
