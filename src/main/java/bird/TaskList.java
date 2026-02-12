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

    public String getTaskListString() {
        if (tasks.isEmpty()) {
            return "you have no tasks";
        }

        StringBuilder sb = new StringBuilder();
        int taskItemNumber = 1;
        for (Task t : tasks) {
           sb.append(taskItemNumber).append(". ").append(t).append("\n");
           taskItemNumber = taskItemNumber + 1;
        }
        return sb.toString().trim();
    }

    public String findMatchingTask(String keyword) {

        String key = keyword.substring(4).trim();
        StringBuilder sb = new StringBuilder();
        int taskItemNumber = 1;

        for (Task t: tasks) {
            if (t.description.contains(key)) {
                sb.append(taskItemNumber).append(". ").append(t).append("\n");
                taskItemNumber = taskItemNumber + 1;
            }
        }

        return sb.toString().trim();
    }

    public String findDueTasks() {
        StringBuilder sb = new StringBuilder();
        int taskItemNumber = 1;

        sb.append("Here are the tasks that are due soon (within 7 days): ").append("\n");

        for (Task t: tasks) {
            if (t.isDueSoon()) {
                sb.append(taskItemNumber).append(". ").append(t).append("\n");
                taskItemNumber = taskItemNumber + 1;
            }
        }

        return sb.toString().trim();
    }

    /**
     * Marks a task as done when given the task number.
     *
     * @param taskNo the task number with reference to the task list.
     */
    public String markTaskAsDone(int taskNo) {
        // test assertion
        assert taskNo > 0 && tasks.size() > taskNo : "taskNo out of bounds";
        if (taskNo > tasks.size()) {
            return "task is out of bounds for the number of tasks you have";
        }
        this.tasks.get(taskNo).markAsDone();
        return "Nice, I've marked this task as done \n" + this.tasks.get(taskNo);
    }

    /**
     * Marks a task as undone when given the task number.
     *
     * @param taskNo the task number with reference to the task list.
     */
    public String markTaskAsUndone(int taskNo) {
        this.tasks.get(taskNo).markAsUndone();
        return "I've marked this task as undone \n" + this.tasks.get(taskNo);
    }

    /**
     * Deletes a task when given the input delete <taskNo></taskNo>.
     *
     * @param input delete <taskNo></taskNo>.
     */
    public String deleteTask(String input) {
        String segments = input.substring(6).trim();
        int index = Integer.parseInt(segments) - 1;

        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        return "Task removed \n" + task;
    }

    /**
     * adds a deadline task to the task list provided the deadline task is provided in correct format to the parser.
     *
     * @param input deadline task (correctly formatted).
     */
    public String addDeadlineTask(String input) throws BirdException {
        try {
            String[] segments = input.substring(8).trim().split(" /by ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(segments[1], formatter);

            Deadline deadline = new Deadline(segments[0], date);
            this.tasks.add(deadline);

            return deadline.toString();
        } catch (DateTimeParseException e) {
            throw new BirdException("Error: date must be in yyyy-MM-dd HHmm format");
        }
    }

    /**
     * adds an event task to the task list provided the event task is provided in correct format to the parser.
     *
     * @param input event task (correctly formatted).
     */
    public String addEventTask(String input) throws BirdException {
        try {
            String[] segments = input.substring(5).trim().split(" /from ");
            String[] timing = segments[1].split(" /to ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            LocalDateTime from = LocalDateTime.parse(timing[0], formatter);
            LocalDateTime to = LocalDateTime.parse(timing[1], formatter);

            Events event = new Events(segments[0], from, to);
            this.tasks.add(event);

            return event.toString();
        } catch (DateTimeParseException e) {
            throw new BirdException("Error: date must be in yyyy-MM-dd HHmm format");
        }
    }


    /**
     * adds a todo task to the task list provided the todo task is provided in correct format to the parser.
     *
     * @param input todo task (correctly formatted).
     */
    public String addToDoTask(String input) {
        String info = input.substring(4).trim();
        ToDos todo = new ToDos(info);

        this.tasks.add(todo);
        return "Todo task added:\n" + todo.toString();
    }
}
