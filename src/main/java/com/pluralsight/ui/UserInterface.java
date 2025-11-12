package com.pluralsight.ui;

import com.pluralsight.repository.ReceiptWriter;
import com.pluralsight.model.Chip;
import com.pluralsight.model.Drink;
import com.pluralsight.model.Order;
import com.pluralsight.model.Sandwich;

import java.util.*;
import java.util.stream.Collectors;

public class UserInterface {

    private final Scanner scan =new Scanner(System.in);


    public void display(){
        boolean running=true;
        while(running){
            System.out.println("""
                1) New Order
                0) Exit
                """);
            System.out.print("your choice: ");
            switch (scan.nextLine()){
                case "1" -> createOrder(new Order());
                case "0" -> running=false;
                default -> System.out.println("invalid choice please try again...");
            }
        }

    }


    public void createOrder(Order order){
        boolean running=true;
        while(running){
            System.out.println("""
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                """);
            System.out.print("your choice: ");
            switch (scan.nextLine()){
                case "1"-> order.addItem(addSandwich());
                case "2"-> order.addItem(addDrink());
                case "3"-> order.addItem(addChips());
                case "4"-> {
                    checkout(order);
                    running=false;
                }
                case "0" -> running=false;
                default ->  System.out.println("invalid choice please try again...");
            }
        }
    }


    public Sandwich addSandwich() {
        Sandwich sandwich = new Sandwich();

        //Get size
        ArrayList<String> items;

        items=createOptions("Mini","Large","Giant");
        sandwich.setSize(askSingleChoice("size bread",items));

        // Get bread type
        items=createOptions("White","Wheat","Rye","Wrap");
        sandwich.setBread(askSingleChoice("bread", items));

        sandwich.setToasted(askYesNo("toasted bread"));


        // Get meats
        items=createOptions("Steak","Ham","Salami","Roasted beef","Chicken","Bacon");
        sandwich.setMeats(askMultipleChoice("meat",items));

        // Extra meat
        if(!sandwich.getMeats().isEmpty()){
            sandwich.setExtraMeat(askYesNo("meat"));
        }
        else{
            sandwich.setExtraMeat(false);
        }

        // Get cheese
        items=createOptions("American","Provolone","Cheddar","Swiss");
        sandwich.setCheeses(askMultipleChoice("cheese", items));

        // Get extra cheese
        if(!sandwich.getCheeses().isEmpty()){
            sandwich.setExtraCheese(askYesNo("cheese"));
        }
        else{
            sandwich.setExtraCheese(false);
        }

        // Get toppings
        items=createOptions("Lettuce","Peppers","Onions","Tomatoes","Jalapenos",
                "Cucumbers","Pickles","Guacamole","Mushrooms");
        sandwich.setToppings(askMultipleChoice("topping",items));

        // Get sauces
        items=createOptions("Mayo","Mustard","Ketchup","Ranch","Thousand islands","Vinaigrette");
        sandwich.setSauces(askMultipleChoice("sauce",items));

        // Get side
        items=createOptions("Au jus","Sauce");
        sandwich.setSides(askMultipleChoice("side",items));

        return sandwich;
    }

    public Drink addDrink(){
        // Get drink
        String size = askSingleChoice("size drink",createOptions("Small","Medium","Large"));
        String flavor=askSingleChoice("flavor",createOptions("Water","Pepsi","Lemonade","Sweet tea"));
        return new Drink(size,flavor);
    }

    public Chip addChips(){
        String type=askSingleChoice("chip",createOptions("Cheetos","Doritos","Ruffles"));

        return new Chip(type);
    }

    public void checkout(Order order){
        System.out.println(order);
        while(true){
            System.out.println("""
                1) Confirm
                2) Cancel
                """);
            System.out.print("your choice: ");
            switch (scan.nextLine().trim().toLowerCase()){
                case "1","confirm":
                    new ReceiptWriter().saveReceipt(order);
                case "2","cancel":
                    return;
                default:
                    System.out.println("invalid choice please try again...");
            }
        }

    }


    public ArrayList<String> createOptions(String... options){

        return new ArrayList<>(Arrays.asList(options));
    }

    public boolean askYesNo(String type){
        while(true){
            System.out.printf("Do you want extra %s (y/n): ",type);
            String choice=scan.nextLine().trim();
            if(choice.equalsIgnoreCase("y")|| choice.equalsIgnoreCase("n")){
                return choice.equalsIgnoreCase("y");
            }
            System.out.println("Invalid choice, please try again.\n");
        }

    }

    public String askSingleChoice(String category, ArrayList<String> items){
        String phrase=(List.of("size bread","size drink").contains(category)? "":"kind of");
        while(true){

            System.out.printf("What %s %s do you prefer?\n",phrase,category);

            items.forEach(n-> System.out.printf("- %s\n",n));

            System.out.print("your choice: ");
            String choice = scan.nextLine().trim().toLowerCase();

            boolean validChoice=items.stream()
                    .map(String::toLowerCase)
                    .anyMatch(n->n.equalsIgnoreCase(choice));

            if(validChoice){
                return choice;
            }
            else if(choice.isEmpty()){
                System.out.printf("you have to choose a %s, please try again.\n",category);
                continue;
            }

            System.out.println("Invalid choice, please try again.\n");
        }
    }

    public ArrayList<String> askMultipleChoice(String category, ArrayList<String> items){

        while(true){

            System.out.printf("What kind of %s do you prefer?\n",category);
            // print items
            System.out.println("(Separate multiple types with commas)");

            items.forEach(n-> System.out.printf("- %s\n",n));

            System.out.print("your choice: ");
            ArrayList<String> choice= createOptions(scan.nextLine().split(","))
                    .stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toCollection(ArrayList::new));


            boolean validChoice = choice.stream()
                    .allMatch(s -> items.stream()
                            .map(String::toLowerCase)
                            .anyMatch(s::equals));

            if(validChoice){

                return choice;
            }
            else if (choice.get(0).isEmpty()) {

                return new ArrayList<>();
            }

            System.out.println("Invalid choice, please try again.\n");
        }
    }
}
