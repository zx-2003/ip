import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String path) {
        this.filePath = Paths.get(path);
    }

    // load the tasks from the file specified
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line: lines) {
                if (line.isBlank()) continue;
                Task task = parse(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("could not load saved tasks.");
        }

        return tasks;
    }

    // save tasks to the file
    public void save(ArrayList<Task> tasks) {
        try {
            Files.createDirectories(filePath.getParent());
            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(t.toFileString());
            }

            Files.write(filePath, lines, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("could not save the tasks");
        }
    }

    // converting lines from the file into a task
    private Task parse(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0];
        boolean done = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new ToDos(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Events(description, parts[3], parts[4]);
                break;
            default:
                return null;
        }
        task.setIsDone(done);
        return task;
    }
}
