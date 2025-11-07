package com.pluralsight.model;

import java.util.ArrayList;

public class Order {
    private ArrayList<Sandwich> sandwiches;
    private ArrayList<Drink> drinks;
    private int chips;


    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = 0;
    }


    public void addSandwich(Sandwich sandwich){
        this.sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink){
        this.drinks.add(drink);
    }

    public void addChip(){
        this.chips++;
    }

    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public int getChips() {
        return chips;
    }

    @Override
    public String toString() {
        return sandwiches.stream()
                .map(Sandwich::toString)
                .reduce("",(a,b)->a+b)  +
                drinks.stream()
                        .map(Drink::toString)
                        .reduce("",(a,b)->a+b)  +
                chips + "chip" + (chips>1 ? "s" : "") + "\n";
    }



}
