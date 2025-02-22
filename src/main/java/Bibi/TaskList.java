package Bibi;

import java.util.ArrayList;

/**
 * Manages a list of tasks in the Bibi application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a formatted string of all tasks.
     *
     * @return A string listing all tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return getEmptyListMessage();
        }
        return getTaskList();
    }

    /**
     * Returns a message indicating that there are no tasks.
     *
     * @return A message stating that the task list is empty.
     */
    public String getEmptyListMessage(){
        return "Meow! No tasks yet!";
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     *
     * @return A string listing each task with its index.
     */
    public String getTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markNotDone();
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param description The description of the Todo task.
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param by The due date/time of the Deadline task.
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the last task in the list.
     *
     * @return The last task in the list.
     */
    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A formatted string of matching tasks.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = findMatchingTasks(keyword);

        if (matchingTasks.isEmpty()) {
            return "Meow! No matching tasks found.";
        }

        return formatMatchingTask(matchingTasks);
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public ArrayList<Task> findMatchingTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String normalizedKeyword = keyword.trim().toLowerCase();

        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(normalizedKeyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Formats a list of matching tasks into a readable string.
     *
     * @param matchingTasks The list of matching tasks.
     * @return A formatted string listing the matching tasks.
     */
    public String formatMatchingTask(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Adds a Todo task and returns a response message.
     *
     * @param description The description of the Todo task.
     * @param ui The UI instance for generating responses.
     * @return A message confirming the addition of the task.
     */
    public String addTodoTask(String description, Ui ui) {
        this.tasks.add(new Todo(description));
        return ui.todoResponse() + this.getLastTask();
    }

    /**
     * Adds a Deadline task and returns a response message.
     *
     * @param input The input string containing the task description and due date.
     * @param ui The UI instance for generating responses.
     * @return A message confirming the addition of the task.
     */
    public String addDeadlineTask(String input, Ui ui) {
        String[] parts = input.split(" /by ");
        this.tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        return ui.deadlineResponse() + this.getLastTask();
    }

    /**
     * Adds an Event task and returns a response message.
     *
     * @param input The input string containing the task description, start time, and end time.
     * @param ui The UI instance for generating responses.
     * @return A message confirming the addition of the task.
     */
    public String addEventTask(String input, Ui ui) {
        String[] parts = input.split(" /from | /to ");
        this.tasks.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        return ui.eventResponse() + this.getLastTask();
    }
}