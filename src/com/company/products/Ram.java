package com.company.products;

public class Ram extends Product {
    private final double WEIGHT = 0.1;
    public Ram(double price, double weight) {
        super(price, weight);
        super.setWeight(WEIGHT);
    }
}
