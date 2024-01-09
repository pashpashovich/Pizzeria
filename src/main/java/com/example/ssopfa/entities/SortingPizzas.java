package com.example.ssopfa.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingPizzas extends Thread {

    private List<Pizza> pizzas;

    public synchronized void sort() {
        pizzas.sort(Comparator.comparingDouble(Pizza::getPrice));
    }

    @Override
    public void run() {
        sort();
    }

    public SortingPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Pizza> getAppliances() {
        return pizzas;
    }
}

