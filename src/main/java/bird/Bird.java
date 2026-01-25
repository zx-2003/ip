package bird;

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
                userInterface.printHorizontalLine();
                Parser parser = new Parser(userInterface.readInput());

                if (parser.checkListCommand()) {
                    taskList.printTaskList();
                }

                // if user enters mark or unmark
                else if (parser.checkMarkCommand()) {
                    String[] segments = parser.input.split(" ");
                    int index = Integer.parseInt(segments[1]) - 1;

                    if (parser.input.startsWith("mark")) {
                        userInterface.markTaskAsDone();
                        taskList.markTaskAsDone(index);
                    } else {
                        userInterface.markTaskAsUndone();
                        taskList.markTaskAsUndone(index);
                    }

                    storage.saveTasks(taskList.tasks);
                }

                // if user enters bye
                else if (parser.checkByeCommand()) {
                    userInterface.printByeMessage();
                    running = false;
                }

                // if user enters delete
                else if (parser.checkDeleteCommand()) {
                    userInterface.removeTask();
                    taskList.deleteTask(parser.input);
                    storage.saveTasks(taskList.tasks);

                    userInterface.taskCounter(taskList.tasks.size());
                }

                // if user enters deadline task
                else if (parser.checkDeadlineTask()) {
                    taskList.addDeadlineTask(parser.input);
                    storage.saveTasks(taskList.tasks);

                    userInterface.taskCounter(taskList.tasks.size());
                }

                // if user enters event task
                else if (parser.checkEventTask()) {
                    taskList.addEventTask(parser.input);
                    storage.saveTasks(taskList.tasks);

                    userInterface.taskCounter(taskList.tasks.size());
                }

                // if user wants to create a to-do task
                else if (parser.checkToDoTask()) {
                    taskList.addToDoTask(parser.input);
                    storage.saveTasks(taskList.tasks);

                    userInterface.taskCounter(taskList.tasks.size());
                }

                else {
                    throw new BirdException();
                }

            } catch (BirdException e) {
                System.out.println(e.getMessage());
            }
            userInterface.printHorizontalLine();
        }
    }
}

