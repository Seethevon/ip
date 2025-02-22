package Bibi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages the storage and retrieval of tasks from a file.
 * This class provides functionality to load tasks from a file,
 * create necessary directories or files if they don't exist,
 * and save tasks back to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     * If the file does not exist, it creates the necessary directory
     * and file before attempting to load tasks.
     *
     * @return A list of tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        File file = new File(filePath);
        createDirectoryIfNeeded(file.getParentFile());
        createFileIfNeeded(file);

        return loadTasksFromFile(file);
    }

    /**
     * Reads tasks from the specified file and parses them into Task objects.
     *
     * @param file The file to read tasks from.
     * @return A list of tasks read from the file.
     */
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

    /**
     * Creates the directory if it does not exist.
     *
     * @param directory The directory to check and create if necessary.
     */
    public void createDirectoryIfNeeded(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Creates the file if it does not exist.
     *
     * @param file The file to check and create if necessary.
     */
    public void createFileIfNeeded(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Meow! Unable to create new file: " + filePath);
            }
        }
    }

    /**
     * Saves the current task list to the specified file.
     * It writes each task in the task list to the file in a specific format.
     *
     * @param taskList The task list to save to the file.
     */
    public void save(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        assert (new File("./data/tasks")).exists() : "The file must exist before it can save";
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