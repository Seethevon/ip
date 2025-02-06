package Bibi;

import java.util.Scanner;

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
     * Runs the Bibi application, reading and executing user commands.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = ui.readCommand(scanner);
            if (userInput.equals("bye")) {
                ui.showGoodbye();
                break;
            }
            Parser.handleCommand(userInput, tasks, ui);
            storage.save(tasks);
        }

        scanner.close();
    }

    /**
     * Main entry point of the Bibi application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Bibi("data/tasks.txt").run();
    }
}

