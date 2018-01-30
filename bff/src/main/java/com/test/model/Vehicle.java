package com.test.model;

public class Vehicle {
    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Vehicle withName(String name) {
        setName(name);
        return this;
    }

    public Vehicle withPrice(String price) {
        setPrice(price);
        return this;
    }
}
