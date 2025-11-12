package com.pluralsight.model;

public class Chip extends MenuItem{

    private String type;

    public Chip(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getTotal() {
        return 1.5;
    }
}
