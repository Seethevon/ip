package Bibi;

/**
 * Represents a task with a description and completion status.
 * Tasks can be marked as done or not done, and their status can be retrieved.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task
     * @throws AssertionError if the description is null.
     */
    public Task(String description) {
        assert description != null : "Description cannot be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" represents a completed task, while " " represents an incomplete task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * showing its completion status and description.
     *
     * @return A formatted string representing the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the task in a file-friendly format for storage.
     *
     * @return A formatted string for saving the task to a file.
     */
    public String toFileFormat() {
        return "Task | " + (isDone ? "1" : "0") + " | " + description;
    }

}