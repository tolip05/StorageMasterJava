package com.company.vehicles;

public class Van extends Vehicle {
    private final int VAN_CAPACITY = 2;
    public Van(int capacity) {
        super(capacity);
        super.setCapacity(VAN_CAPACITY);
    }
}
