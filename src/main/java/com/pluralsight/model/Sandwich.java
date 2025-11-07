package com.pluralsight.model;

import java.util.ArrayList;

public class Sandwich {

    private String size;
    private String bread;
    private ArrayList<String> meats;
    private boolean extraMeat;
    private String cheese;
    private boolean extraCheese;
    private ArrayList<String> toppings;
    private ArrayList<String> sauces ;
    private ArrayList<String> sides;

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

    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
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

    @Override
    public String toString() {



        return "\n\n\n" +
                size+ " size    " + bread+ " bread\n"+
                "meats: " + displayList(meats) + (extraMeat? "  extra meat ✅ ":"") +"\n"+
                cheese+" cheese" +  (extraCheese? "   extra cheese ✅ ":"") + "\n"+
                "toppings: " + displayList(toppings) + "\n"+
                "sides: " + displayList(sides) + "\n"+
                "sauces: " + (sides.contains("sauce")? displayList(sauces):" no sauce") +
                "\n\n";
    }
    public String displayList(ArrayList<String> list){
        String text="";
        for(String s:list){
            text+=s+", ";
        }
        text=(list!=null? text.substring(0, text.length()-2) : " ---");
        return text;
    }
}
