package com.company.products;

public abstract class Product {
    private double price;

    private double weight;

    protected Product(double price, double weight) {
        this.setPrice(price);
        this.weight = weight;
    }

    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {

        if (price < 0){
            throw new IllegalArgumentException("Price cannot be negative!");
        }
        this.price = price;
    }

    public double getWeight() {
        return this.weight;
    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }
}
