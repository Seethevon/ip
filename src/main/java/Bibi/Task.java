package Bibi;


public class Task {
    protected String description;
    protected boolean isDone;
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    public Task(String description) {
        assert description != null : "Description cannot be null";
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toFileFormat() {
        return "Task | " + (isDone ? "1" : "0") + " | " + description;
    }

}