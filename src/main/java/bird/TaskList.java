package bird;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * Represents a list of tasks that the user interacts with.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints a numbered list of tasks that the user has created.
     */
    public void printTaskList() {
        int taskItemNumber = 1;
        for (Task t : tasks) {
            System.out.println(taskItemNumber + ". " + t);
            taskItemNumber = taskItemNumber + 1;
        }
    }

    /**
     * Prints a list of tasks given a keyword as a string.
     *
     * @param keyword string that the user provides.
     */
    public void findMatchingTask(String keyword) {
        String key = keyword.substring(4).trim();
        int taskItemNumber = 1;
        for (Task t: tasks) {
            if (t.description.contains(key)) {
                System.out.println(taskItemNumber + ". " + t);
                taskItemNumber = taskItemNumber + 1;
            }
        }
    }

    /**
     * Marks a task as done when given the task number.
     *
     * @param taskNo the task number with reference to the task list.
     */
    public void markTaskAsDone(int taskNo) {
        this.tasks.get(taskNo).markAsDone();
        System.out.println(this.tasks.get(taskNo));
    }

    /**
     * Marks a task as undone when given the task number.
     *
     * @param taskNo the task number with reference to the task list.
     */
    public void markTaskAsUndone(int taskNo) {
        this.tasks.get(taskNo).markAsUndone();
        System.out.println(this.tasks.get(taskNo));
    }

    /**
     * Deletes a task when given the input delete <taskNo></taskNo>.
     *
     * @param input delete <taskNo></taskNo>.
     */
    public void deleteTask(String input) {
        String segments = input.substring(6).trim();
        int index = Integer.parseInt(segments) - 1;
        System.out.println(this.tasks.get(index));
        this.tasks.remove(index);
    }

    /**
     * adds a deadline task to the task list provided the deadline task is provided in correct format to the parser.
     *
     * @param input deadline task (correctly formatted).
     */
    public void addDeadlineTask(String input) throws BirdException {
        try {
            String[] segments = input.substring(8).trim().split(" /by ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(segments[1], formatter);
            Deadline deadline = new Deadline(segments[0], date);
            this.tasks.add(deadline);
            System.out.println(deadline);
        } catch (DateTimeParseException e) {
            throw new BirdException("Error: date must be in yyyy-MM-dd HHmm format");
        }
    }

    /**
     * adds an event task to the task list provided the event task is provided in correct format to the parser.
     *
     * @param input event task (correctly formatted).
     */
    public void addEventTask(String input) throws BirdException {
        try {
            String[] segments = input.substring(5).trim().split(" /from ");
            String[] timing = segments[1].split(" /to ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(timing[0], formatter);
            LocalDateTime to = LocalDateTime.parse(timing[1], formatter);
            Events event = new Events(segments[0], from, to);
            this.tasks.add(event);
            System.out.println(event);
        } catch (DateTimeParseException e) {
            throw new BirdException("Error: date must be in yyyy-MM-dd HHmm format");
        }
    }

    /**
     * adds a todo task to the task list provided the todo task is provided in correct format to the parser.
     *
     * @param input todo task (correctly formatted).
     */
    public void addToDoTask(String input) {
        String info = input.substring(4).trim();
        ToDos todo = new ToDos(info);
        this.tasks.add(todo);
        System.out.println(todo);
    }
}
