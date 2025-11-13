package com.pluralsight.ui;

import com.pluralsight.InputHandler;
import com.pluralsight.repository.ReceiptWriter;
import com.pluralsight.model.Chip;
import com.pluralsight.model.Drink;
import com.pluralsight.model.Order;
import com.pluralsight.model.Sandwich;

import java.util.*;

public class UserInterface {

    private final Scanner scan = new Scanner(System.in);
    private final static InputHandler input = new InputHandler();


    public void displayMenu() {
        boolean running = true;
        while (running) {
            System.out.println("""
                    1) New Order
                    0) Exit
                    """);
            System.out.print("your choice: ");
            switch (scan.nextLine()) {
                case "1" -> createOrder(new Order());
                case "0" -> running = false;
                default -> System.out.println("invalid choice please try again...");
            }
        }

    }


    public void createOrder(Order order) {
        boolean running = true;
        while (running) {
            System.out.println("""
                    1) Add Sandwich
                    2) Add Drink
                    3) Add Chips
                    4) Checkout
                    0) Cancel Order
                    """);
            System.out.print("your choice: ");
            switch (scan.nextLine()) {
                case "1" -> order.addItem(addSandwich());
                case "2" -> order.addItem(addDrink());
                case "3" -> order.addItem(addChips());
                case "4" -> {
                    checkout(order);
                    running = false;
                }
                case "0" -> running = false;
                default -> System.out.println("invalid choice please try again...");
            }
        }
    }


    public Sandwich addSandwich() {
        Sandwich sandwich = new Sandwich();
        boolean running=true;
        while (running){
            System.out.println("""
                    1) BLT
                        Large Toasted White Bread
                        Bacon
                        Cheddar Cheese
                        Lettuce, Tomato
                        Ranch
                    2) Philly Cheese Steak
                        Large Toasted White Bread
                        Steak
                        American Cheese
                        Peppers
                        Mayo
                    3) Build Your Sandwich
                    """);
            System.out.print("your choice: ");
            switch (scan.nextLine()){
                case "1" :
                    return buildBLT();
                case "2" :
                    return buildPhillyCheeseSteak();
                case "3" :
                    running=false;
                    break;
                default :
                    System.out.println("invalid choice please try again...");
            }

        }

        //Get size
        ArrayList<String> items;

        items = createOptions("Mini", "Large", "Giant");
        sandwich.setSize(input.askSingleChoice("size bread", items));

        // Get bread type
        items = createOptions("White", "Wheat", "Rye", "Wrap");
        sandwich.setBread(input.askSingleChoice("bread", items));

        sandwich.setToasted(input.askYesNo("toasted bread"));


        // Get meats
        items = createOptions("Steak", "Ham", "Salami", "Roasted beef", "Chicken", "Bacon");
        sandwich.setMeats(input.askMultipleChoice("meat", items));

        // Extra meat
        if (!sandwich.getMeats().isEmpty()) {
            sandwich.setExtraMeat(input.askYesNo("meat"));
        } else {
            sandwich.setExtraMeat(false);
        }

        // Get cheese
        items = createOptions("American", "Provolone", "Cheddar", "Swiss");
        sandwich.setCheeses(input.askMultipleChoice("cheese", items));

        // Get extra cheese
        if (!sandwich.getCheeses().isEmpty()) {
            sandwich.setExtraCheese(input.askYesNo("cheese"));
        } else {
            sandwich.setExtraCheese(false);
        }

        // Get toppings
        items = createOptions("Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapenos",
                "Cucumbers", "Pickles", "Guacamole", "Mushrooms");
        sandwich.setToppings(input.askMultipleChoice("topping", items));

        // Get sauces
        items = createOptions("Mayo", "Mustard", "Ketchup", "Ranch", "Thousand islands", "Vinaigrette");
        sandwich.setSauces(input.askMultipleChoice("sauce", items));

        // Get side
        items = createOptions("Au jus", "Sauce");
        sandwich.setSides(input.askMultipleChoice("side", items));

        return sandwich;
    }

    public Drink addDrink() {
        // Get drink
        String size = input.askSingleChoice("size drink", createOptions("Small", "Medium", "Large"));
        String flavor = input.askSingleChoice("flavor", createOptions("Water", "Pepsi", "Lemonade", "Sweet tea"));
        return new Drink(size, flavor);
    }

    public Chip addChips() {
        String type = input.askSingleChoice("chip", createOptions("Cheetos", "Doritos", "Ruffles"));

        return new Chip(type);
    }

    public void checkout(Order order) {
        System.out.println(order);
        while (true) {
            System.out.println("""
                    1) Confirm
                    2) Cancel
                    """);
            System.out.print("your choice: ");
            switch (scan.nextLine().trim().toLowerCase()) {
                case "1", "confirm":
                    new ReceiptWriter().saveReceipt(order);
                case "2", "cancel":
                    return;
                default:
                    System.out.println("invalid choice please try again...");
            }
        }

    }


    public ArrayList<String> createOptions(String... options) {
        return new ArrayList<>(Arrays.asList(options));
    }

    public Sandwich buildBLT(){

        return new Sandwich(
                "large",
                "white",
                true,
                new ArrayList<>(List.of("bacon")),
                new ArrayList<>(List.of("cheddar")),
                new ArrayList<>(List.of("lettuce","tomatoes")),
                new ArrayList<>(List.of("ranch"))
        );
    }

    public Sandwich buildPhillyCheeseSteak(){
        return new Sandwich(
                "large",
                "white",
                true,
                new ArrayList<>(List.of("steak")),
                new ArrayList<>(List.of("american")),
                new ArrayList<>(List.of("peppers")),
                new ArrayList<>(List.of("mayo"))
        );
    }

}
