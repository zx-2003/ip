import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Bird {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        Storage storage = new Storage("./data/bird.txt");
        ArrayList<Task> tasks = storage.load();

        Ui userInterface = new Ui();
        userInterface.welcomeMessage();

        while (running) {
            try {
                userInterface.horizontalLine();
                String input = scanner.nextLine();

                // if the user types in list
                if (input.equals("list")) {
                    userInterface.printTaskList(tasks);
                }

                // if the user wants to mark a task, probably need exception handling
                else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    String[] segments = input.split(" ");
                    int index = Integer.parseInt(segments[1]) - 1;
                    if (input.startsWith("mark")) {
                        tasks.get(index).markAsDone();
                        userInterface.markTaskAsDone();
                    } else {
                        tasks.get(index).markAsUndone();
                        userInterface.markTaskAsUndone();
                    }
                    storage.save(tasks);
                    System.out.println(tasks.get(index));
                }

                // if the user types in bye
                else if (input.equals("bye")) {
                    userInterface.byeMessage();
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
                        userInterface.removeTask();
                        System.out.println(removedTask);
                        userInterface.taskCounter(tasks.size());
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

                        if (segments.length < 2) {
                            throw new BirdException("Error: deadline must be in format:\n" +
                                    "deadline <desc> /by yyyy-MM-dd HHmm");
                        }

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime date = LocalDateTime.parse(segments[1], formatter);
                            Deadline deadline = new Deadline(segments[0], date);
                            tasks.add(deadline);
                            storage.save(tasks);
                            System.out.println(deadline);
                            userInterface.taskCounter(tasks.size());
                        } catch (DateTimeParseException e) {
                            throw new BirdException("Error: date must be in yyyy-MM-dd HHmm format");
                        }

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
                        if (segments.length < 2) {
                            throw new BirdException("Error: event must be in format:\n" +
                                    "event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
                        }
                        String[] timing = segments[1].split(" /to ");
                        if (timing.length < 2) {
                            throw new BirdException("Error: event must be in format:\n" +
                                    "event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
                        }

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime from = LocalDateTime.parse(timing[0], formatter);
                            LocalDateTime to = LocalDateTime.parse(timing[1], formatter);
                            Events event = new Events(segments[0], from, to);
                            tasks.add(event);
                            storage.save(tasks);
                            System.out.println(event);
                            userInterface.taskCounter(tasks.size());
                        } catch (DateTimeParseException e) {
                            throw new BirdException("Error: date must be in yyyy-mm-dd HHmm format");
                        }

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
                        System.out.println(todo);
                        userInterface.taskCounter(tasks.size());
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
            userInterface.horizontalLine();
        }
    }
}

