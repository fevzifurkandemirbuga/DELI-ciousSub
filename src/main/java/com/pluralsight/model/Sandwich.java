package com.pluralsight.model;

import java.util.ArrayList;

public class Sandwich {

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
                %s size    %s bread
                meats: %s %s
                %s: cheese %s
                toppings: %s
                sides: %s
                sauces: %s
                ________________________________________________
                """,size,bread,displayList(meats),(extraMeat? "  extra meat ":""),
                displayList(cheeses),(extraCheese? "   extra cheese":""),displayList(toppings),
                displayList(sides),(sides.contains("sauce")? displayList(sauces):" no sauce"));
    }

    public String displayList(ArrayList<String> list){
        String text=String.join(", ",list);

        text=(!list.isEmpty()? text.substring(0, text.length()-2) : " ---");
        return text;
    }
}
