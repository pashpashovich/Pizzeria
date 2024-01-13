package com.example.ssopfa.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Customer extends User implements Serializable {
    /**
     * Surname, name, patronymic surname of the customer
     */
    private final String FIO;
    /**
     * Has access to the app or not
     */
    private List<Pizza> cart;
    private double cartSum;

    public Customer(String login, String password, String FIO, boolean hasAccess, List<Pizza> cart) {
        super(login, password, hasAccess);
        this.cart = cart;
        this.FIO = FIO;
    }

    public String getFIO() {
        return FIO;
    }

    public void addToCart(Pizza pizza) throws IOException {
        if (cart == null) cart = new ArrayList<>();
        cart.add(pizza);
        Alerts.showSuccessAlert("Пицца " + pizza.getName() + " успешно добавлена в вашу корзину)");
        Admin.resetExactCustomer(this);
    }

    public double calculateCartSum() {
        if (cart == null) cart = new ArrayList<>();
        if (!cart.isEmpty()) {
            cartSum = 0;
            Calculable calc = p -> cartSum + p.getPrice();
            for (Pizza p : cart) {
                cartSum = calc.calculate(p);
            }
        }
        return cartSum;
    }

    public List<Pizza> getCart() {
        return cart;
    }

    public void removeFromCart(String name, double size, double price) throws IOException {
        if (!cart.isEmpty()) {
            Pizza pizzaToRemove = null;
            for (Pizza pizza : cart) {
                if (pizza.getName().equals(name) && pizza.getSize() == size && pizza.getPrice() == price) {
                    pizzaToRemove = pizza;
                    break;
                }
            }
            if (pizzaToRemove != null) {
                cart.remove(pizzaToRemove);
                Admin.resetExactCustomer(this);
            }
        }
    }
}

