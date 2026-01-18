public class BirdException extends Exception {

    public BirdException() {
        super("Sorry I don't understand what is that supposed to mean");
    }

    public BirdException(String message) {
        super(message);
    }
}
