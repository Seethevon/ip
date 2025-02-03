public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String getData() {
        return (isDone ? "T" : "F") + " | " + this.description;
    }

    public static Task fromString(String data) {
        String[] parts = data.split(" \\| ");
        boolean isDone = parts[0].equals("T");
        String description = parts[1];
        Task task = new Task(description);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
}
