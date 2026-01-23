import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Bird {
    public static void main(String[] args) {
        boolean running = true;
        Storage storage = new Storage("./data/bird.txt");
        ArrayList<Task> tasks = storage.load();

        Ui userInterface = new Ui();
        userInterface.welcomeMessage();

        while (running) {
            try {
                userInterface.horizontalLine();
                String input = userInterface.readInput();

                Parser parser = new Parser(input);
                if (parser.isListCommand()) {
                    userInterface.printTaskList(tasks);
                }

                // if the user wants to mark a task, probably need exception handling
                else if (parser.isMarkCommand()) {
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
                else if (parser.isByeCommand()) {
                    userInterface.byeMessage();
                    running = false;
                }

                // if the user deletes a task
                else if (parser.isDeleteCommand()) {
                        String segments = input.substring(6).trim();
                        int index = Integer.parseInt(segments) - 1;
                        Task removedTask = tasks.remove(index);
                        userInterface.removeTask();
                        System.out.println(removedTask);
                        userInterface.taskCounter(tasks.size());
                        storage.save(tasks);
                }

                // if the user wants to create a deadline task
                else if (parser.isDeadlineTask()) {
                    try {
                        String[] segments = input.substring(8).trim().split(" /by ");
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
                }

                // if the user wants to create an event task
                else if (parser.isEventTask()) {
                    String[] segments = input.substring(5).trim().split(" /from ");
                    String[] timing = segments[1].split(" /to ");

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
                }

                // if user wants to create a to-do task
                else if (input.startsWith("todo")) {
                    String info = input.substring(4).trim();
                    ToDos todo = new ToDos(info);
                    tasks.add(todo);
                    storage.save(tasks);
                    System.out.println(todo);
                    userInterface.taskCounter(tasks.size());
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

