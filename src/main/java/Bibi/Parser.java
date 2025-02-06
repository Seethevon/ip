package Bibi;

/**
 * Handles user input parsing and command execution.
 */
public class Parser {
    /**
     * Processes and executes user commands.
     *
     * @param input The user input command.
     * @param tasks The task list to modify.
     * @param ui    The UI handler for displaying messages.
     * @throws BibiException If it is an unknown input.
     */
    public static void handleCommand(String input, TaskList tasks, Ui ui) {
        try {
            if (input.equals("list")) {
                ui.showMessage(tasks.listTasks());
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
                tasks.markTask(taskNumber);
                ui.showMessage("Good job! Task marked as done:\n" + tasks.getTask(taskNumber));
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                tasks.unmarkTask(taskNumber);
                ui.showMessage("Task marked as not done:\n" + tasks.getTask(taskNumber));
            } else if (input.startsWith("todo ")) {
                tasks.addTodo(input.substring(5).trim());
                ui.showMessage("Added a Todo:\n" + tasks.getLastTask());
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks.addDeadline(parts[0].trim(), parts[1].trim());
                ui.showMessage("Added a Deadline:\n" + tasks.getLastTask());
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
                ui.showMessage("Added an Event:\n" + tasks.getLastTask());
            } else if (input.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                ui.showMessage("Meow! Removed this task:\n" + tasks.deleteTask(taskNumber));
            } else {
                throw new BibiException("Meow! No clue what you just said.");
            }
        } catch (Exception e) {
            ui.showMessage("Meow! Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Parses a task from a file line.
     *
     * @param line The line read from the file.
     * @return A Task object corresponding to the file data.
     */
    public static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("D")) {
            task = new Deadline(description, parts[3]);
        } else {
            task = new Event(description, parts[3], parts[4]);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }
}