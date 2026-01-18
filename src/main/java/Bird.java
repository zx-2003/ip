import java.util.ArrayList;
import java.util.Scanner;

public class Bird {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Bird");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");

        while (running) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________");

            // if the user types in list
            if (input.equals("list")) {
                int taskItemNumber = 1;
                for (Task t : tasks) {
                    System.out.println(taskItemNumber + ". " + t);
                    taskItemNumber = taskItemNumber + 1;
                }
            }

            // if the user wants to mark a task, probably need exception handling
            else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] segments = input.split(" ");
                int index = Integer.parseInt(segments[1]) - 1;

                if (input.startsWith("mark")) {
                    tasks.get(index).markAsDone();
                } else {
                    tasks.get(index).markAsUndone();
                }
                System.out.println(tasks.get(index));
            }

            // if the user types in bye
            else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                running = false;
            }

            // if the user wants to create a deadline task
            else if (input.startsWith("deadline")) {
                // to cut off the word deadline from the task
                String info = input.substring(9);
                String[] segments = info.split(" /by ");
                Deadline deadline = new Deadline(segments[0], segments[1]);
                tasks.add(deadline);
                System.out.println(deadline + "\n" + "Now you have " + tasks.size() + " tasks in the list");
            }

            // if user wants to create a to-do task
            else if (input.startsWith("todo")) {
                String info = input.substring(5);
                ToDos todo = new ToDos(info);
                tasks.add(todo);
                System.out.println(todo + "\n" + "Now you have " + tasks.size() + " tasks in the list");
            }

            // if the user wants to create an event task
            else if (input.startsWith("event")) {
                String info = input.substring(6);
                String[] segments = info.split(" /from ");
                String[] timing = segments[1].split(" /to ");
                Events event = new Events(segments[0], timing[0], timing[1]);
                tasks.add(event);
                System.out.println(event + "\n" + "Now you have " + tasks.size() + " tasks in the list");
            }

            // if the user types in a new task
            else {
                // tasks.add(new Task(input));
                // System.out.println("added: " + input + "\n" + "Now you have " + tasks.size() + " tasks in the list");
                System.out.println("sorry I dont recognize that command / task");
            }
            System.out.println("____________________________________________");
        }
    }
}

