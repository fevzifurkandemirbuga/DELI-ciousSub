package com.pluralsight.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Order {

    private final ArrayList<MenuItem> items = new ArrayList<>();


    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }


    public double getTotal() {

        return items.stream()
                .map(MenuItem::getTotal)
                .reduce(0.0, Double::sum);
    }


    @Override
    public String toString() {

        String sandwichs = items.stream()
                .filter(i -> i instanceof Sandwich)
                .map(Object::toString)
                .reduce("", (a, b) -> a + "\n" + b);

        String drinks = items.stream()
                .filter(i -> i instanceof Drink)
                .map(Object::toString)
                .reduce("", (a, b) -> a + "\n" + b);

        String chips = items.stream()
                .filter(i -> i instanceof Chip)
                .map(i -> ((Chip) i).getType())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .map(c -> c.getValue() + " " + c.getKey() + "\n")
                .reduce("", (a, b) -> a + b);

        return String.format("""
                %s
                %s
                ------------------------------
                %s
                ------------------------------
                Total: $%.2f
                """, sandwichs, drinks, chips, getTotal());
    }
}
