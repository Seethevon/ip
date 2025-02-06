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
            return "Meow! No tasks yet!";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markNotDone();
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    public void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task getLastTask() {
        return tasks.get(tasks.size() - 1);
    }

    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        String normalizedKeyword = keyword.trim().toLowerCase();

        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(normalizedKeyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "Meow! No matching tasks found.";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        return sb.toString();
    }
}