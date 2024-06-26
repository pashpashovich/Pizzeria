package com.example.ssopfa.entities;

import java.io.Serializable;

public class Pizza implements Serializable {
    private String name;
    private double price;
    private double size;

    public String getName() {
        return name;
    }

    public Pizza(String name, double price,double size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString(){
        return "Название: " + name+" Размер: " + size + " Цена: " + price;
    }
}

