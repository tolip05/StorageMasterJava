package com.company.products;

public class HardDrive extends Product {
    private static final double WEIGHT = 1;
    public HardDrive(double price, double weight) {
        super(price, weight);
        super.setWeight(WEIGHT);
    }
}
