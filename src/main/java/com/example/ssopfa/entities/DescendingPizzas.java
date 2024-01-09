package com.example.ssopfa.entities;

import java.util.Comparator;
import java.util.List;

public class DescendingPizzas implements Runnable {
    private List<Pizza> pizzas;

    public synchronized void sort() {
        pizzas.sort(Comparator.comparingDouble(Pizza::getPrice).reversed());
    }

    @Override
    public void run() {
        sort();
    }

    public DescendingPizzas(List<Pizza> appliances) {
        this.pizzas = appliances;
    }

    public List<Pizza> getAppliances() {
        return pizzas;
    }
}
