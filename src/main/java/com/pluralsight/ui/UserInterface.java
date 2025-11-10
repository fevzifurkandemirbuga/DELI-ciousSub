package com.pluralsight.ui;

import com.pluralsight.fileRepository.ReceiptWriter;
import com.pluralsight.model.Drink;
import com.pluralsight.model.Order;
import com.pluralsight.model.Sandwich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
                case "1"-> order.addSandwich(addSandwich());
                case "2"-> order.addDrink(addDrink());
                case "3"-> order.addChip(addChips());
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
        ArrayList<String> items=new ArrayList<>();
        items.add("mini");
        items.add("large");
        items.add("gaint");
        sandwich.setSize(askSingleChoice("size",items));

        // Get bread type
        items.clear();
        items.add("white");
        items.add("wheat");
        items.add("rye");
        items.add("wrap");
        sandwich.setBread(askSingleChoice("bread", items));

        // Get meats
        items.clear();
        items.add("steak");
        items.add("ham");
        items.add("salami");
        items.add("roasted beef");
        items.add("chicken");
        items.add("bacon");
        sandwich.setMeats(askMultipleChoice("meat",items));

        // Extra meat
        if(sandwich.getMeats()!=null){
            sandwich.setExtraMeat(askYesNo("meat"));
        }
        else{
            sandwich.setExtraMeat(false);
        }

        // Get cheese
        items.clear();
        items.add("american");
        items.add("provolone");
        items.add("cheddar");
        items.add("swiss");
        sandwich.setCheeses(askMultipleChoice("cheese", items));

        // Get extra cheese
        if(sandwich.getCheeses()!=null){
            sandwich.setExtraCheese(askYesNo("cheese"));
        }
        else{
            sandwich.setExtraCheese(false);
        }

        // Get toppings
        items.clear();
        items.add("lettuce");
        items.add("peppers");
        items.add("onions");
        items.add("tomatoes");
        items.add("jalapenos");
        items.add("cucumbers");
        items.add("pickles");
        items.add("qucamole");
        items.add("mushrooms");
        sandwich.setToppings(askMultipleChoice("topping",items));

        // Get side
        items.clear();
        items.add("au jus");
        items.add("sauce");
        sandwich.setSides(askMultipleChoice("side",items));

        // Get sauces
        if(sandwich.getSides()!=null && sandwich.getSides().contains("sauce")){
            items.clear();
            items.add("mayo");
            items.add("mustard");
            items.add("ketchup");
            items.add("ranch");
            items.add("thousand islands");
            items.add("vinaigrette");
            sandwich.setSauces(askMultipleChoice("sauce",items));
        }
        return sandwich;
    }

    public Drink addDrink(){
        // Get drink
        String size = askSingleChoice("drink",new ArrayList<>(
                Arrays.asList("small","medium","large")));
        String flavor=askSingleChoice("flavor",new ArrayList<>(
                Arrays.asList("water","pepsi","lemonade","dr. pepper","Sweet Tea")
        ));
        return new Drink(size,flavor);

    }

    public int addChips(){
        System.out.print("How many chip do you want to add?:  ");
        int numOfChips= scan.nextInt();
        scan.nextLine();
        return numOfChips;
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


//    public <T> T askToCustomer(String type, ArrayList<String> items, T returnType){
//          template
//                System.out.println("""
//            What kind of meats do you prefer?
//            (Separate multiple types with commas)
//            - steak
//            - ham
//            - salami
//            - roast beef
//            - chicken
//            - bacon
//            """);
//        System.out.print("your choice: ");
//        String[] meats = scan.nextLine().toLowerCase().split(",");
//        for (String meat : meats) {
//            sandwich.addMeat(meat.trim());
//        }
//
//        while(true){
//            // Get yes/no
//            if(returnType instanceof Boolean){
//                System.out.printf("Do you want extra %s (y/n): ",type);
//                boolean result=scan.nextLine().trim().equalsIgnoreCase("y");
//                return (T) Boolean.valueOf(result);
//
//            }
//
//            System.out.printf("What kind of %s do you prefer?\n",type);
//
//            // single choice
//            if(returnType instanceof String ){
//
//                items.forEach(n-> System.out.printf("- %s\n",n));
//                System.out.print("your choice: ");
//                String choice = scan.nextLine().trim().toLowerCase();
//                if(items.contains(choice)){
//                    return (T) choice;
//                }
//            }
//
//            // multiple choice
//            else if(returnType instanceof ArrayList){
//                System.out.println("(Separate multiple types with commas)");
//                items.forEach(n-> System.out.printf("- %s\n",n));
//                System.out.print("your choice: ");
//                String[] choice = scan.nextLine().toLowerCase().split(",");
//                return (T) Arrays.asList(choice);
//            }
//        }
//
//    }

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

    public String askSingleChoice(String category,ArrayList<String> items){
        String phrase=(List.of("size","drink").contains(category)? "size":"kind of");
        while(true){

            System.out.printf("What %s %s do you prefer?\n",phrase,category);
            items.forEach(n-> System.out.printf("- %s\n",
                    n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase()));
            System.out.print("your choice: ");
            String choice = scan.nextLine().trim().toLowerCase();
            if(items.contains(choice)){
                return choice;
            }
            else if(choice.isEmpty()){
                System.out.printf("you have to choose a %s\n",category);;
            }

            System.out.println("Invalid choice, please try again.\n");
        }
    }

    public ArrayList<String> askMultipleChoice(String category,ArrayList<String> items){
        String phrase=(List.of("size","drink").contains(category)? "size":"kind of");
        while(true){

            System.out.printf("What %s %s do you prefer?\n",phrase,category);
            System.out.println("(Separate multiple types with commas)");
            items.forEach(n-> System.out.printf("- %s\n",
                    n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase()));
            System.out.print("your choice: ");
            String[] choice = scan.nextLine().toLowerCase().split(", ");
            ArrayList<String> result = new ArrayList<String>(Arrays.asList(choice));
            if(items.containsAll(result)){
                return result;
            }
            else if (choice.length==1){
                return null;
            }

            System.out.println("Invalid choice, please try again.\n");

        }
    }
}
