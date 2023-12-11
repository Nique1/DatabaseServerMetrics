package package1.userInput;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    public UserInput() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput(String input) {
        System.out.println(input + ": ");
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

}
