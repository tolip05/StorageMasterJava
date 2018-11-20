package com.company.products;

public class SolidStateDrive extends Product {
    private final double WEIGHT = 0.2;
    public SolidStateDrive(double price, double weight) {
        super(price, weight);
        super.setWeight(WEIGHT);
    }
}
