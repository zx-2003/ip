import java.util.Scanner;

public class Bird {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________");
        System.out.println("    Hello! I'm Bird");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            String input = scanner.nextLine();
            System.out.println("    ____________________________________________");
            if (input.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                running = false;
            } else {
                System.out.println("    " + input);
            }
            System.out.println("    ____________________________________________");
        }
    }
}

