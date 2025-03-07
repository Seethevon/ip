package Bibi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a task with a deadline in the Bibi task management application.
 * This task has a due date and can be postponed.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Task description.
     * @param by Due date in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != null : "Description cannot be null";
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Returns the deadline task in file storage format.
     *
     * @return File-formatted string representation.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets the due date of the task.
     *
     * @return The due date as a LocalDateTime object.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Postpones the deadline by a specified amount of time.
     *
     * @param amount The amount of time to extend the deadline.
     * @param unit The unit of time (e.g., days, hours, minutes).
     */
    public void postpone(long amount, ChronoUnit unit) {
        this.by = this.by.plus(amount, unit);
    }

}
