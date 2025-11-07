package com.pluralsight.ui;

import com.pluralsight.model.Receipt;
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
            switch (scan.nextLine()){
                case "1" -> createOrder();
                case "0" -> running=false;
                default -> System.out.println("invalid choice please try again...");
            }
        }

    }


    public void createOrder(){
        boolean running=true;
        while(running){
            System.out.println("""
                1) Add Sandwich
                2) Add Drink
                3) Add Chips
                4) Checkout
                0) Cancel Order
                """);
            switch (scan.nextLine()){
                case "1"-> addSandwich();
                case "2"-> addDrink();
                case "3"-> addChips();
                case "4"-> checkout();
                case "0"-> running=false;
                default ->  System.out.println("invalid choice please try again...");
            }
        }
    }


    public void addSandwich() {
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
        items.add("wye");
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
        sandwich.setCheese(askSingleChoice("cheese", items));

        // Get extra cheese
        if(sandwich.getCheese()!=null){
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
        items.add("aujus");
        items.add("sauce");
        sandwich.setSides(askMultipleChoice("side",items));

        // Get sauces
        if(sandwich.getSides().contains("sauce")){
            items.clear();
            items.add("mayo");
            items.add("mustard");
            items.add("ketchup");
            items.add("ranch");
            items.add("thousand islands");
            items.add("vinaigrette");
            sandwich.setSauces(askMultipleChoice("sauce",items));
        }
        System.out.println(sandwich);
    }



    public void addDrink(){
        System.out.println("""
                What kind of size do you prefer
                1) Small
                2) Medium
                3) Large
                """);
        switch (scan.nextLine().toLowerCase()){
            case "1", "small" -> {}
            case "2", "medium" ->{}
            case "3", "large" ->{}
            default -> {}
        }

    }
    public void addChips(){

    }
    public void checkout(){

    }



//    public <T> T askToCustomer(String type, ArrayList<String> items, T returnType){
//          template
//        //        System.out.println("""
////            What kind of meats do you prefer?
////            (Separate multiple types with commas)
////            - steak
////            - ham
////            - salami
////            - roast beef
////            - chicken
////            - bacon
////            """);
////        System.out.print("your choice: ");
////        String[] meats = scan.nextLine().toLowerCase().split(",");
////        for (String meat : meats) {
////            sandwich.addMeat(meat.trim());
////        }
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

    public String askSingleChoice(String type,ArrayList<String> items){
        while(true){

            System.out.printf("What kind of %s do you prefer?\n",type);
            items.forEach(n-> System.out.printf("- %s\n",
                    n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase()));
            System.out.print("your choice: ");
            String choice = scan.nextLine().trim().toLowerCase();
            if(items.contains(choice)){
                return choice;
            }
            else if(choice.isEmpty()){
                return null;
            }

            System.out.println("Invalid choice, please try again.\n");
        }
    }

    public ArrayList<String> askMultipleChoice(String type,ArrayList<String> items){
        while(true){

            System.out.printf("What kind of %s do you prefer?\n",type);
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
