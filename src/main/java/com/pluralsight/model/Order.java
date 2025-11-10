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

    public void addChip(int number){
        this.chips+=number;
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

    public double getTotal(){
        double total=0;
        for(Sandwich s:sandwiches){
            double toppingPrice=s.getMeats().size()+0.75+(s.isExtraMeat()?0.5:0)+(s.isExtraCheese()?0.30:0);
            switch (s.getSize()){
                case "mini" -> total+=5.50+toppingPrice;
                case "large" -> total+=7+(toppingPrice*2);
                case "gaint" -> total+=8.50+(toppingPrice*3);
            }
        }
        for(Drink d:drinks){
            switch (d.getSize()){
                case "small" -> total+=2;
                case "large" -> total+=2.5;
                case "gaint" -> total+=3;
            }
        }

        total+= chips*1.5;

        return total;

    }


    @Override
    public String toString() {
        return sandwiches.stream()
                .map(Sandwich::toString)
                .reduce("",(a,b)->a+b)  +
                drinks.stream()
                        .map(Drink::toString)
                        .reduce("",(a,b)->a+b)  +
                chips + " chip" + (chips>1 ? "s" : "") + "\n"+
                String.format("Total: %.2f",getTotal());
    }



}
