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
                int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
                tasks.markTask(taskNumber);
                return ui.markTaskResponse() + tasks.getTask(taskNumber);
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                tasks.unmarkTask(taskNumber);
                return ui.unmarkTaskResponse() + tasks.getTask(taskNumber);
            } else if (input.startsWith("todo ")) {
                tasks.addTodo(input.substring(5).trim());
                return ui.todoResponse() + tasks.getLastTask();
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks.addDeadline(parts[0].trim(), parts[1].trim());
                return ui.deadlineResponse() + tasks.getLastTask();
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
                return ui.eventResponse() + tasks.getLastTask();
            } else if (input.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                return ui.deleteResponse() + tasks.deleteTask(taskNumber);
            } else if (input.startsWith("find ")) { // New condition for "find"
                String keyword = input.substring(5).trim();
                String results = tasks.findTasks(keyword);
                return results;
            } else if (input.equals("bye")) {
                return ui.sayGoodbye();
            } else {
                throw new BibiException("Meow! No clue what you just said.");
            }
        } catch (Exception e) {
            return "Meow! Something went wrong: " + e.getMessage();
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