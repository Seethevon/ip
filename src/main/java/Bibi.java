import java.util.Scanner;
import java.util.ArrayList;
public class Bibi {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("                           ≽^•⩊•^≼");
        System.out.println("       Meow. It's me Bibi and I'm back from the dead!");
        System.out.println("         Since I'm no longer there to help you out.");
        System.out.println("          I will haunt you to do your work instead.");
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the list of actions you can do:");
        System.out.println(" - list");
        System.out.println(" - bye");
        System.out.println(" - todo");
        System.out.println(" - deadline");
        System.out.println(" - event");
        System.out.println(" - mark");
        System.out.println(" - unmark");
        System.out.println(" - delete");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Goodbye Owner. ˙◠˙");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    if (tasks.isEmpty()) {
                        System.out.println(" No tasks yet! Add one to get started.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1; // Extract and parse task number
                        if (taskNumber < 0 || taskNumber >= tasks.size()) {
                            System.out.println("____________________________________________________________");
                            System.out.println(" Meow! Task number is out of range.");
                            System.out.println("____________________________________________________________");
                        } else {
                            tasks.get(taskNumber).markDone();
                            System.out.println("____________________________________________________________");
                            System.out.println(" Good job! :");
                            System.out.println("   " + tasks.get(taskNumber));
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Meow! Task number must be a valid integer.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (input.startsWith("unmark ")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1; // Extract and parse task number
                        if (taskNumber < 0 || taskNumber >= tasks.size()) {
                            System.out.println("____________________________________________________________");
                            System.out.println(" Meow! Task number is out of range.");
                            System.out.println("____________________________________________________________");
                        } else {
                            tasks.get(taskNumber).markNotDone();
                            System.out.println("____________________________________________________________");
                            System.out.println(" DO YOUR WORK :");
                            System.out.println("   " + tasks.get(taskNumber));
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Meow! Task number must be a valid integer.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new BibiException("Meow! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Task added, don't forget to do it :");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(8).split(" /by ");
                    if (parts.length < 2) {
                        throw new BibiException("Meow! The deadline format should be: deadline <description> /by <time>");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Task added, don't forget to do it :");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.substring(5).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new BibiException("Meow! The event format should be: event <description> /from <start time> /to <end time>");
                    }
                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("____________________________________________________________");
                    System.out.println(" Task added, don't forget to do it :");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println(" Now you have " + tasks.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete ")) {
                    try {
                        int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;

                        if (taskNumber < 0 || taskNumber >= tasks.size()) {
                            System.out.println("____________________________________________________________");
                            System.out.println(" Meow! Task number is out of range.");
                            System.out.println("____________________________________________________________");
                        } else {
                            Task removedTask = tasks.remove(taskNumber);
                            System.out.println("____________________________________________________________");
                            System.out.println(" Meow! I've removed this task:");
                            System.out.println("   " + removedTask);
                            System.out.println(" Now you have " + tasks.size() + " task(s) in the list.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Meow! Task number must be a valid integer.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    throw new BibiException("Meow! No clue what you just said. It is not within the list of actions you can do. Try Again.");
                }
            } catch (BibiException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Meow! Something went wrong: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}

