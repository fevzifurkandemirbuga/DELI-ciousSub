package com.pluralsight.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order {

    private ArrayList<MenuItem> items = new ArrayList<>();


    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }


    public double getTotal() {

        return items.stream()
                .mapToDouble(MenuItem::getTotal)
                .sum();
    }


    @Override
    public String toString() {

        String sandwiches = items.stream()
                .filter(i -> i instanceof Sandwich)
                .map(Object::toString)
                .collect(Collectors.joining("\n", "", ""));

        String drinks = items.stream()
                .filter(i -> i instanceof Drink)
                .map(Object::toString)
                .collect(Collectors.joining("\n", "","\n------------------------------"));

        String chips = items.stream()
                .filter(i -> i instanceof Chip)
                .map(i -> ((Chip) i).getType())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .map(c -> c.getValue() + " " + c.getKey())
                .collect(Collectors.joining("\n", "", "\n------------------------------"));


        return String.format("""
                ------------------------------
                %s
                %s
                %s
                Total: $%.2f
                """, sandwiches, drinks, chips, getTotal());
    }
}
