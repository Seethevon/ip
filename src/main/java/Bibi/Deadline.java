package Bibi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getBy() {
        return by;
    }
}
