package bird;

import java.util.Scanner;

public class Ui {
    // this will handle most of the logic for what the user sees
    private final Scanner scanner = new Scanner(System.in);

    public String readInput() {
        return scanner.nextLine();
    }

    public void welcomeMessage() {
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Bird");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________");
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void removeTask() {
        System.out.println("I've removed this task");
    }

    public void markTaskAsDone() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void markTaskAsUndone() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public void taskCounter(int size) {
        System.out.println("You now have " + size + " tasks in the list");
    }
}
