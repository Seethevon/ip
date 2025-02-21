package Bibi;

/**
 * Represents a task without a date or time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
        assert description != null : "Description cannot be null";
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the Todo task in file storage format.
     *
     * @return File-formatted string representation.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
