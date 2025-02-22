package Bibi;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Handles user input parsing and command execution.
 */
public class Parser {

    /**
     * Processes and executes user commands.
     *
     * @param input The user input command.
     * @param tasks The task list to modify.
     * @param ui The UI handler for displaying messages.
     * @throws BibiException If it is an unknown input.
     * @returns A response based on the command executed.
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
            } else if (input.startsWith("snooze ")) {
                return snoozeTask(input, tasks, ui);
            } else if (input.equals("bye")) {
                return ui.sayGoodbye();
            } else {
                throw new BibiException("No clue what you just said.");
            }
        } catch (Exception e) {
            return "Meow! Something went wrong: " + e.getMessage();
        }
    }

    /**
     * Handles the snooze command, allowing the user to postpone a Deadline task.
     *
     * @param input The snooze command input.
     * @param tasks The task list to modify.
     * @param ui The UI handler for displaying messages.
     * @return A message indicating whether the task was successfully snoozed.
     */
    public static String snoozeTask(String input, TaskList tasks, Ui ui) {
        try {
            String[] parts = input.split(" ", 3);
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            String durationStr = parts[2].trim();

            Task task = tasks.getTask(taskNumber);

            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                long durationMillis = parseDuration(durationStr);
                if (durationMillis <= 0) {
                    return "Meow! Invalid snooze duration.";
                }

                if (durationStr.contains("hour")) {
                    deadlineTask.postpone(durationMillis / (60 * 60 * 1000), ChronoUnit.HOURS);
                } else if (durationStr.contains("day")) {
                    deadlineTask.postpone(durationMillis / (24 * 60 * 60 * 1000), ChronoUnit.DAYS);
                }

                return "Task snoozed until: " + deadlineTask.getBy().format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            }

            return "Meow! Only Deadline tasks can be snoozed.";
        } catch (Exception e) {
            return "Meow! Invalid task number or duration.";
        }
    }

    /**
     * Parses a duration string and converts it to milliseconds.
     *
     * @param durationStr The duration string (e.g., "2 hour", "3 day").
     * @return The duration in milliseconds.
     */
    public static long parseDuration(String durationStr) {
        if (durationStr.contains("hour")) {
            int hours = Integer.parseInt(durationStr.split(" ")[0]);
            return hours * 60 * 60 * 1000;
        } else if (durationStr.contains("day")) {
            int days = Integer.parseInt(durationStr.split(" ")[0]);
            return days * 24 * 60 * 60 * 1000;
        }
        return 0;
    }

    /**
     * Marks a task as done.
     *
     * @param input The mark command input.
     * @param tasks The task list to modify.
     * @param ui The UI handler for displaying messages.
     * @return A message indicating the task has been marked as done.
     */
    public static String markTask(String input, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
            tasks.markTask(taskNumber);
            return ui.markTaskResponse() + tasks.getTask(taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Meow! Invalid task number.";
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param input The unmark command input.
     * @param tasks The task list to modify.
     * @param ui The UI handler for displaying messages.
     * @return A message indicating the task has been unmarked as done.
     */
    public static String unmarkTask(String input, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            tasks.unmarkTask(taskNumber);
            return ui.unmarkTaskResponse() + tasks.getTask(taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Meow! Invalid task number.";
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input The delete command input.
     * @param tasks The task list to modify.
     * @param ui The UI handler for displaying messages.
     * @return A message indicating the task has been deleted.
     */

    public static String deleteTask(String input, TaskList tasks, Ui ui) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            return ui.deleteResponse() + tasks.deleteTask(taskNumber);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Meow! Invalid task number.";
        }
    }

    /**
     * Finds tasks that contain a keyword in their description.
     *
     * @param input The find command input with the keyword.
     * @param tasks The task list to search through.
     * @return A string listing the tasks that match the keyword.
     */
    public static String findTasks(String input, TaskList tasks) {
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

    /**
     * Creates a task based on the type and data provided.
     *
     * @param type The type of the task (e.g., "T" for Todo, "D" for Deadline).
     * @param description The description of the task.
     * @param parts The additional parts needed to create the task.
     * @return A Task object corresponding to the provided type.
     * @throws IllegalArgumentException If the task type is invalid.
     */
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