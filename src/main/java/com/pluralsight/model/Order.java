package com.pluralsight.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order {
    private ArrayList<Sandwich> sandwiches;
    private ArrayList<Drink> drinks;
    private ArrayList<Chip> chips;


    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chips = new ArrayList<>();
    }


    public void addSandwich(Sandwich sandwich){
        this.sandwiches.add(sandwich);
    }

    public void addDrink(Drink drink){
        this.drinks.add(drink);
    }

    public void addChip(Chip chip){
        this.chips.add(chip);
    }

    public ArrayList<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public ArrayList<Chip> getChips() {
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

        total+= chips.size()*1.5;

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

                chips.stream()
                        .map(Chip::getType)
                        .collect(Collectors.groupingBy(c->c,Collectors.counting()))
                        .entrySet().stream()
                        .map(c->c.getValue()+" "+c.getKey()+"\n")
                        .reduce("",(a,b)->a+b) +
                " ________________________________________________\n"+

                String.format("Total: %.2f",getTotal());
    }



}
