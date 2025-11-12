package com.pluralsight.model;

public class Drink extends MenuItem {

    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return size + " size " + flavor + "\n";
    }

    @Override
    public double getTotal() {
        return switch (this.size.toLowerCase()) {
            case "small" -> 2.0;
            case "medium" -> 2.5;
            case "large" -> 3.0;
            default -> throw new IllegalStateException("Unexpected value: " + this.size.toLowerCase());
        };
    }
}
