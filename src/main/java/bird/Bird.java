package bird;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Main entry point for the chatbot.
 */
public class Bird {
    public static void main(String[] args) {
        boolean running = true;
        Storage storage = new Storage("./data/bird.txt");

        ArrayList<Task> tasks = storage.load();
        TaskList taskList = new TaskList(tasks);

        Ui userInterface = new Ui();
        userInterface.welcomeMessage();

        while (running) {
            try {
                userInterface.horizontalLine();
                Parser parser = new Parser(userInterface.readInput());

                if (parser.isListCommand()) {
                    taskList.printTaskList();
                }

                // if the user wants to mark a task, probably need exception handling
                else if (parser.isMarkCommand()) {
                    String[] segments = parser.input.split(" ");
                    int index = Integer.parseInt(segments[1]) - 1;

                    if (parser.input.startsWith("mark")) {
                        userInterface.markTaskAsDone();
                        taskList.markTaskAsDone(index);
                    } else {
                        userInterface.markTaskAsUndone();
                        taskList.markTaskAsUndone(index);
                    }
                    storage.save(taskList.tasks);
                }

                // if the user types in bye
                else if (parser.isByeCommand()) {
                    userInterface.byeMessage();
                    running = false;
                }

                // if the user deletes a task
                else if (parser.isDeleteCommand()) {
                    userInterface.removeTask();
                    taskList.deleteTask(parser.input);
                    userInterface.taskCounter(taskList.tasks.size());
                    storage.save(taskList.tasks);
                }

                // if the user wants to create a deadline task
                else if (parser.isDeadlineTask()) {
                    try {
                        taskList.addDeadlineTask(parser.input);
                        storage.save(taskList.tasks);
                        userInterface.taskCounter(taskList.tasks.size());
                    } catch (DateTimeParseException e) {
                        throw new BirdException("Error: date must be in yyyy-MM-dd HHmm format");
                    }
                }

                // if the user wants to create an event task
                else if (parser.isEventTask()) {
                    try {
                        taskList.addEventTask(parser.input);
                        storage.save(taskList.tasks);
                        userInterface.taskCounter(taskList.tasks.size());
                    } catch (DateTimeParseException e) {
                        throw new BirdException("Error: date must be in yyyy-mm-dd HHmm format");
                    }
                }

                // if user wants to create a to-do task
                else if (parser.isToDoTask()) {
                    taskList.addToDoTask(parser.input);
                    storage.save(taskList.tasks);
                    userInterface.taskCounter(taskList.tasks.size());
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

