package com.company.products;

public class Gpu extends Product {
    private final double GPU_WEIGHT = 0.7;

    public Gpu(double price, double weight) {
        super(price, weight);
        super.setWeight(GPU_WEIGHT);
    }
}
