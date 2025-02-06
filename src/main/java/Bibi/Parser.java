package Bibi;

public class Parser {

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
            } else if (input.startsWith("find ")) { // New condition for "find"
                String keyword = input.substring(5).trim();
                String results = tasks.findTasks(keyword);
                ui.showMessage(results);
            } else {
                throw new BibiException("Meow! No clue what you just said.");
            }
        } catch (Exception e) {
            ui.showMessage("Meow! Something went wrong: " + e.getMessage());
        }
    }

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