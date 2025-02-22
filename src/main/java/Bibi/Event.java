package Bibi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Bibi task management application.
 * This task has a start time, end time, and can be marked as completed or not.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description A brief description of the event.
     * @param from The start time of the event in the format "yyyy-MM-dd HHmm".
     * @param to The end time of the event in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null : "Description cannot be null";
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the event task.
     * The format includes the task type, completion status, description, start time, and end time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Converts the event task into a file storage format.
     * The format follows: "E | completion_status | description | start_time | end_time".
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " +
                to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time as a {@link LocalDateTime} object.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time as a {@link LocalDateTime} object.
     */
    public LocalDateTime getTo() {
        return to;
    }
}
