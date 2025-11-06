package com.pluralsight.ui;

import com.pluralsight.model.Receipt;

import java.util.ArrayList;
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


    public void addSandwich(){

        System.out.println("""
                What kind of bread do you prefer
                1) White
                2) Wheat
                3) Rye
                4) Wrap
                """);
        switch (scan.nextLine().toLowerCase()){
            case "1","white" -> {}
            case "2", "wheat" ->{}
            case "3", "rye" ->{}
            case "4", "wrap" -> {}
            default -> {}
        }

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








}
