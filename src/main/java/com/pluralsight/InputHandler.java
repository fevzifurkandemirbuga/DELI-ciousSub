package com.pluralsight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputHandler {

    private final Scanner scan = new Scanner(System.in);

    public boolean askYesNo(String type) {
        while (true) {
            System.out.printf("Do you want extra %s (y/n): ", type);
            String choice = scan.nextLine().trim();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")) {
                return choice.equalsIgnoreCase("y");
            }
            System.out.println("Invalid choice, please try again.\n");
        }

    }

    public String askSingleChoice(String category, ArrayList<String> items) {
        String phrase = (List.of("size bread", "size drink").contains(category) ? "" : "kind of");
        while (true) {

            System.out.printf("What %s %s do you prefer?\n", phrase, category);

            items.forEach(n -> System.out.printf("- %s\n", n));

            System.out.print("your choice: ");
            String choice = scan.nextLine().trim().toLowerCase();

            boolean validChoice = items.stream()
                    .map(String::toLowerCase)
                    .anyMatch(n -> n.equalsIgnoreCase(choice));

            if (validChoice) {
                return choice;
            } else if (choice.isEmpty()) {
                System.out.printf("you have to choose a %s, please try again.\n", category);
                continue;
            }

            System.out.println("Invalid choice, please try again.\n");
        }
    }

    public ArrayList<String> askMultipleChoice(String category, ArrayList<String> items) {

        while (true) {

            System.out.printf("What kind of %s do you prefer?\n", category);
            // print items
            System.out.println("(Separate multiple types with commas)");

            items.forEach(n -> System.out.printf("- %s\n", n));

            System.out.print("your choice: ");
            ArrayList<String> choice = Arrays.stream((scan.nextLine().split(",")))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toCollection(ArrayList::new));


            boolean validChoice = choice.stream()
                    .allMatch(s -> items.stream()
                            .map(String::toLowerCase)
                            .anyMatch(s::equals));

            if (validChoice) {

                return choice;
            } else if (choice.get(0).isEmpty()) {

                return new ArrayList<>();
            }

            System.out.println("Invalid choice, please try again.\n");
        }
    }


}
