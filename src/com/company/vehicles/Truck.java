package com.company.vehicles;

public class Truck extends Vehicle {
    private final int TRUCK_CAPACITY = 5;
    public Truck(int capacity) {
        super(capacity);
        super.setCapacity(TRUCK_CAPACITY);
    }
}
