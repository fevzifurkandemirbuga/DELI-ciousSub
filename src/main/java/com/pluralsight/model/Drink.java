package com.pluralsight.model;

public class Drink {

    private String size;

    public Drink() {
    }

    public Drink(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size + "size drink\n"+
                "________________________________________________\n";
    }
}
