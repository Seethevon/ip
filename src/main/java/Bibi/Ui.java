package Bibi;

public class Ui {
    private static final String GREETING_MESSAGE = "Meow! I'm Bibi and I will help you with organise your tasks!";
    private static final String GOODBYE_MESSAGE = "Goodbye :(";
    private static final String MARK_DONE_MESSAGE = "Good job! Bibi is proud of you:\n";
    private static final String UNMARK_DONE_MESSAGE = "Bibi thinks you should try harder:\n";
    private static final String TODO_ADDED_MESSAGE = "Meow, another task to do:\n";
    private static final String DEADLINE_ADDED_MESSAGE = "Meow, another deadline to finish:\n";
    private static final String EVENT_ADDED_MESSAGE = "Meow, another event to attend:\n";
    private static final String DELETE_TASK_MESSAGE = "Meow! Removed this task:\n";
    private static final String COMMANDS_MESSAGE = "Meow! These are the commands that I know!:\n" +
            "1. List: Lists out the tasks in a numbered format. (list)\n" +
            "2. Mark: Marks a task on the list. (mark [task number])\n" +
            "3. Unmark: Unmarks a task on the list. (unmark [task number])\n" +
            "4. Delete: Deletes a task on the list. (delete [task number])\n" +
            "5. Find: Insert a keyword and it will search for tasks that contain it. (find [keyword])\n" +
            "6. Todo: Adds a Todo task. (todo [task])\n" +
            "7. Deadline: Adds a Deadline task containing a due date. (deadline [task] /by[date yyyy-mm-dd HHmm])\n" +
            "8. Event: Adds an Event task containing from and to. (event [task] /from[date yyyy-mm-dd HHmm] /to[date yyyy-mm-dd HHmm])\n" +
            "9. Snooze: Allows user to postpone a task's deadline. (snooze [task number] [number] hour/day)";


    public String greetUser() {
        return GREETING_MESSAGE;
    }

    public String sayGoodbye() {
        return GOODBYE_MESSAGE;
    }

    public String markTaskResponse() {
        return MARK_DONE_MESSAGE;
    }

    public String unmarkTaskResponse() {
        return UNMARK_DONE_MESSAGE;
    }

    public String todoResponse() {
        return TODO_ADDED_MESSAGE;
    }

    public String deadlineResponse() {
        return DEADLINE_ADDED_MESSAGE;
    }

    public String eventResponse() {
        return EVENT_ADDED_MESSAGE;
    }

    public String deleteResponse() {
        return DELETE_TASK_MESSAGE;
    }

    public String commandsResponse() {
        return COMMANDS_MESSAGE;
    }
}