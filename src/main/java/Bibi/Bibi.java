package Bibi;

import java.util.Scanner;
public class Bibi {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bibi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }
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

    public static void main(String[] args) {
        new Bibi("data/tasks.txt").run();
    }
}

