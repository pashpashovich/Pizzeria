package com.example.ssopfa.entities;

import java.io.IOException;
import java.util.*;

public class PizzaList {
    private static List<Pizza> pizzaList = new ArrayList<>();

    public static List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public PizzaList() {
        pizzaList = SerializationOfPizzas.deserializator();
    }


    public static void removeFromList(String name, double size, double price) throws IOException {
        if (!pizzaList.isEmpty()) {
            Pizza pizzaToRemove = null;
            for (Pizza pizza : pizzaList) {
                if (pizza.getName().equals(name) && pizza.getSize() == size && pizza.getPrice() == price) {
                    pizzaToRemove = pizza;
                    break;
                }
            }
            if (pizzaToRemove != null) {
                pizzaList.remove(pizzaToRemove);
                SerializationOfPizzas.serializator((ArrayList<Pizza>) pizzaList);
            }
        }
    }

    public static void addPizza(Pizza pizza) throws IOException {
        pizzaList.add(pizza);
        SerializationOfPizzas.serializator((ArrayList<Pizza>) pizzaList);
    }
}
