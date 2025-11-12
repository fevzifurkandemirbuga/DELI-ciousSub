package com.pluralsight.model;

import java.util.ArrayList;

public class Sandwich extends MenuItem {

    private String size;
    private String bread;
    private ArrayList<String> meats;
    private boolean extraMeat;
    private ArrayList<String> cheeses;
    private boolean extraCheese;
    private ArrayList<String> toppings;
    private ArrayList<String> sauces ;
    private ArrayList<String> sides;
    private boolean toasted;

    public Sandwich() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public ArrayList<String> getMeats() {
        return meats;
    }

    public void setMeats(ArrayList<String> meats) {
        this.meats = meats;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public ArrayList<String> getCheeses() {
        return cheeses;
    }

    public void setCheeses(ArrayList<String> cheeses) {
        this.cheeses = cheeses;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public ArrayList<String> getSauces() {
        return sauces;
    }

    public void setSauces(ArrayList<String> sauces) {
        this.sauces = sauces;
    }

    public ArrayList<String> getSides() {
        return sides;
    }

    public void setSides(ArrayList<String> sides) {
        this.sides = sides;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public String toString() {

        return String.format("""
                ________________________________________________
                Size: %s
                Bread: %s   %s
                meats: %s   %s
                cheese: %s   %s
                toppings: %s
                sides: %s
                sauces: %s
                ________________________________________________""",
                getSize(),
                getBread(),
                (isToasted()? "toasted": "not toasted"),
                (meats.isEmpty()?"no meat":displayList(meats)),
                (extraMeat? "extra meat ":""),
                (cheeses.isEmpty()?"no cheese":displayList(cheeses)),
                (extraCheese? "extra cheese":""),
                (toppings.isEmpty())?"no topping":displayList(toppings),
                (sides.isEmpty()?"no sides":displayList(sides)),
                (sauces.isEmpty()? "no sauce": displayList(sauces)));
    }

    public String displayList(ArrayList<String> list){

        return String.join(", ",list);
    }

    @Override
    public double getTotal() {
        double toppingPrice= meats.size() * 1.0+
                (extraMeat ? 0.5:0)+
                cheeses.size()*0.75+
                (extraCheese ? 0.3:0);
        return switch (this.size.toLowerCase()) {
            case "mini" -> 5.50 + toppingPrice;
            case "large" -> 7.00 + toppingPrice * 2;
            case "giant" -> 8.50 + toppingPrice * 3;
            default -> throw new IllegalStateException("Unexpected value: " + size.toLowerCase());
        };
    }
}
