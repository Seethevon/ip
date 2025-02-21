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
     * @returns A response
     */
    public static String handleCommand(String input, TaskList tasks, Ui ui) {
        try {
            if (input.equals("list")) {
                return tasks.listTasks();
            } else if (input.equals("hi")) {
                return ui.greetUser();
            } else if (input.equals("commands")) {
                return ui.commandsResponse();
            } else if (input.startsWith("mark ")) {
                return markTask(input, tasks, ui);
            } else if (input.startsWith("unmark ")) {
                return unmarkTask(input, tasks, ui);
            } else if (input.startsWith("todo ")) {
                return tasks.addTodoTask(input.substring(5).trim(), ui);
            } else if (input.startsWith("deadline ")) {
                return tasks.addDeadlineTask(input.substring(9).trim(), ui);
            } else if (input.startsWith("event ")) {
                return tasks.addEventTask(input.substring(6).trim(), ui);
            } else if (input.startsWith("delete ")) {
                return deleteTask(input, tasks, ui);
            } else if (input.startsWith("find ")) { // New condition for "find"
                return findTasks(input, tasks);
            } else if (input.equals("bye")) {
                return ui.sayGoodbye();
            } else {
                throw new BibiException("No clue what you just said.");
            }
        } catch (Exception e) {
            return "Meow! Something went wrong: " + e.getMessage();
        }
    }

    private static String markTask(String input, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
            tasks.markTask(taskNumber);
            return ui.markTaskResponse() + tasks.getTask(taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Meow! Invalid task number.";
        }
    }

    private static String unmarkTask(String input, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            tasks.unmarkTask(taskNumber);
            return ui.unmarkTaskResponse() + tasks.getTask(taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Meow! Invalid task number.";
        }
    }

    private static String deleteTask(String input, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            return ui.deleteResponse() + tasks.deleteTask(taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Meow! Invalid task number.";
        }
    }

    private static String findTasks(String input, TaskList tasks) {
        String keyword = input.substring(5).trim();
        return tasks.findTasks(keyword);
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

        Task task = createTask(type, description, parts);

        if (isDone) {
            task.markDone();
        }
        return task;
    }

    public static Task createTask(String type, String description, String[] parts) {
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            return new Deadline(description, parts[3]);
        case "E":
            return new Event(description, parts[3], parts[4]);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

}