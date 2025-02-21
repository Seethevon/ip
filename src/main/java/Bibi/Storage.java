package Bibi;

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
        File file = new File(filePath);
        createDirectoryIfNeeded(file.getParentFile());
        createFileIfNeeded(file);

        return loadTasksFromFile(file);
    }

    public ArrayList<Task> loadTasksFromFile(File file) {
        ArrayList<Task> tasks = new ArrayList<>();

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

    public void createDirectoryIfNeeded(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void createFileIfNeeded(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Meow! Unable to create new file: " + filePath);
            }
        }
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