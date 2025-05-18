package org.example.cricketGame.Utils.input;

import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider{
    private final Scanner scanner;

    public ConsoleInputProvider(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    @Override
    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
    public void close() {
        scanner.close();
    }
}
