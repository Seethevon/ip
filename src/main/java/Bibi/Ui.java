package Bibi;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("                           ≽^•⩊•^≼");
        System.out.println("       Meow. It's me Bibi.Bibi and I'm back from the dead!");
        System.out.println("         Since I'm no longer there to help you out.");
        System.out.println("          I will haunt you to do your work instead.");
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Goodbye Owner. ˙◠˙");
        System.out.println("____________________________________________________________");
    }

    public String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}