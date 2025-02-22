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

    /**
     * Processes user input and returns the corresponding response.
     * The input is parsed, the appropriate action is executed,
     * and the updated task list is saved to storage.
     *
     * @param input The user command input.
     * @return The response message after processing the command.
     * @throws AssertionError if the input is null.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";
        String response = Parser.handleCommand(input, tasks, ui);
        storage.save(tasks);
        return response;
    }
}

