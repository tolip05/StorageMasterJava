package com.company.vehicles;

public class Semi extends Vehicle {
    private final int SEMI_CAPACITY = 10;
    public Semi(int capacity) {
        super(capacity);
        super.setCapacity(SEMI_CAPACITY);
    }
}
