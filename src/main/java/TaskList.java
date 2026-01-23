import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTaskList() {
        int taskItemNumber = 1;
        for (Task t : tasks) {
            System.out.println(taskItemNumber + ". " + t);
            taskItemNumber = taskItemNumber + 1;
        }
    }

    public void markTaskAsDone(int taskNo) {
        this.tasks.get(taskNo).markAsDone();
        System.out.println(this.tasks.get(taskNo));
    }

    public void markTaskAsUndone(int taskNo) {
        this.tasks.get(taskNo).markAsUndone();
        System.out.println(this.tasks.get(taskNo));
    }

    public void deleteTask(String input) {
        String segments = input.substring(6).trim();
        int index = Integer.parseInt(segments) - 1;
        System.out.println(this.tasks.get(index));
        this.tasks.remove(index);
    }

    public void addDeadlineTask(String input) {
        String[] segments = input.substring(8).trim().split(" /by ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime date = LocalDateTime.parse(segments[1], formatter);
        Deadline deadline = new Deadline(segments[0], date);
        this.tasks.add(deadline);
        System.out.println(deadline);
    }

    public void addEventTask(String input) {
        String[] segments = input.substring(5).trim().split(" /from ");
        String[] timing = segments[1].split(" /to ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse(timing[0], formatter);
        LocalDateTime to = LocalDateTime.parse(timing[1], formatter);
        Events event = new Events(segments[0], from, to);
        this.tasks.add(event);
        System.out.println(event);
    }

    public void addToDoTask(String input) {
        String info = input.substring(4).trim();
        ToDos todo = new ToDos(info);
        this.tasks.add(todo);
        System.out.println(todo);
    }
}
