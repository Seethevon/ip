import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Parser.parseTaskFromFile(line));
            }
        } catch (IOException e) {
            System.out.println("Meow! Unable to load tasks from file.");
        }

        return tasks;
    }

    public void save(TaskList taskList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Meow! Unable to save tasks to file.");
        }
    }
}