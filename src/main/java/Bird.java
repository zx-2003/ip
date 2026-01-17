import java.util.ArrayList;
import java.util.Scanner;

public class Bird {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("    ____________________________________________");
        System.out.println("    Hello! I'm Bird");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________");

        while (running) {
            String input = scanner.nextLine();
            System.out.println("    ____________________________________________");
            if (input.equals("list")) {
                // first need to find a way to increment, second write item next to it
                int taskItemNumber = 1;
                for (String task : tasks) {
                    System.out.println("    " + taskItemNumber + ". " + task);
                    taskItemNumber = taskItemNumber + 1;
                }
            } else if (input.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                running = false;
            } else {
                tasks.add(input);
                System.out.println("    added: " + input);
            }
            System.out.println("    ____________________________________________");
        }
    }
}

