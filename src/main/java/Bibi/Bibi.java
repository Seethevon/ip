package Bibi;

/**
 * Main class for the Bibi application.
 * Handles task management through user input.
 */
public class Bibi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bibi instance with a specified file path for storage.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Bibi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        String response = Parser.handleCommand(input, tasks, ui);
        storage.save(tasks);
        return response;
    }
}

