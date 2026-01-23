import java.util.ArrayList;
import java.util.Scanner;

public class Bird {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Storage storage = new Storage("./data/bird.txt");
        ArrayList<Task> tasks = storage.load();

        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Bird");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");

        while (running) {
            try {
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
                    storage.save(tasks);
                    System.out.println(tasks.get(index));
                }

                // if the user types in bye
                else if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    running = false;
                }

                // if the user deletes a task
                else if (input.startsWith("delete")) {
                    try {
                        String segments = input.substring(6).trim();
                        if (segments.isEmpty()) {
                            throw new BirdException("Error: you must choose to delete a task");
                        }
                        int index = Integer.parseInt(segments) - 1;
                        Task removedTask = tasks.remove(index);
                        System.out.println("I've removed this task");
                        System.out.println(removedTask);
                        System.out.println("You now have " + tasks.size() + " tasks in the list");
                        storage.save(tasks);
                    } catch (BirdException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // if the user wants to create a deadline task
                else if (input.startsWith("deadline")) {
                    try {
                        String info = input.substring(8).trim();
                        if (info.isEmpty()) {
                            throw new BirdException("Error: deadline task must have a description");
                        }
                        String[] segments = info.split(" /by ");
                        Deadline deadline = new Deadline(segments[0], segments[1]);
                        tasks.add(deadline);
                        storage.save(tasks);
                        System.out.println(deadline + "\n" + "Now you have " + tasks.size() + " tasks in the list");
                    } catch (BirdException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // if user wants to create a to-do task
                else if (input.startsWith("todo")) {
                    try {
                        String info = input.substring(4).trim();
                        if (info.isEmpty()) {
                            throw new BirdException("Error: todo task must have a description");
                        }
                        ToDos todo = new ToDos(info);
                        tasks.add(todo);
                        storage.save(tasks);
                        System.out.println(todo + "\n" + "Now you have " + tasks.size() + " tasks in the list");
                    } catch (BirdException e) {
                        System.out.println(e.getMessage());
                    }
                }

                // if the user wants to create an event task
                else if (input.startsWith("event")) {
                    try {
                        String info = input.substring(5).trim();
                        if (info.isEmpty()) {
                            throw new BirdException("Error: event task must have a description");
                        }
                        String[] segments = info.split(" /from ");
                        String[] timing = segments[1].split(" /to ");
                        Events event = new Events(segments[0], timing[0], timing[1]);
                        tasks.add(event);
                        storage.save(tasks);
                        System.out.println(event + "\n" + "Now you have " + tasks.size() + " tasks in the list");
                    } catch (BirdException e) {
                        System.out.println(e.getMessage());
                    }
                }

                else {
                    throw new BirdException();
                }
            } catch (BirdException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________");
        }
    }
}

