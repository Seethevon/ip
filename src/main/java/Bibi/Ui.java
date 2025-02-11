package Bibi;

import java.util.Scanner;

public class Ui {
    public String greetUser() {
        return "Meow! I'm Bibi and I will help you with organise your tasks!";
    }

    public String sayGoodbye() {
        return "Goodbye :(";
    }

    public String markTaskResponse() {
        return "Good job! Task marked as done:\n";
    }

    public String unmarkTaskResponse() {
        return "Task marked as not done:\n";
    }

    public String todoResponse() {
        return "Added a Todo:\n";
    }

    public String deadlineResponse() {
        return "Added a Deadline:\n";
    }

    public String eventResponse() {
        return "Added an Event:\n";
    }

    public String deleteResponse() {
        return "Meow! Removed this task:\n";
    }

    public String commandsResponse() {
        return "Meow! These are the commands that I know!:\n" +
                "1. List: Lists out the tasks in a numbered format. (list) \n" +
                "2. Mark: Marks a task on the list. (mark [task number]) \n" +
                "3. Unmark: Unmarks a task on the list. (unmark [task number]) \n" +
                "4. Delete: Deletes a task on the list. (delete [task numebr]) \n" +
                "5. Find: Insert a keyword and it will search for tasks that contain it. (find [keyword]) \n" +
                "6. Todo: Adds a Todo task. (todo [task]) \n" +
                "7. Deadline: Adds a Deadline task containing a due date. (deadline [task] /by[date yyyy-mm-dd HHmm] \n" +
                "8. Event: Adds an Event task containing from and to. (event [task] /from[date yyyy-mm-dd HHmm] /to[date yyyy-mm-dd HHmm] \n";
    }
}