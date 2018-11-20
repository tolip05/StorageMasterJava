package com.company.storages;

import com.company.vehicles.Truck;
import com.company.vehicles.Vehicle;

import java.util.List;

public class AutomatedWarehouse extends Storage {
    private final int AUTOMATED_WAREHOUSE_CAPACITY = 1;
    private final int AUTOMATED_WAREHOUSE_GARAGE_SLOTS = 2;
    private static Vehicle[] defaultVehicle =
            {
                    new Truck(0),
                    null
            };
    public AutomatedWarehouse(String name, int capacity, int garageSlots, List<Vehicle> vehicles) {
        super(name, capacity, garageSlots, vehicles);
        super.setCapacity(AUTOMATED_WAREHOUSE_CAPACITY);
        super.setGarageSlots(AUTOMATED_WAREHOUSE_GARAGE_SLOTS);
        super.setGarage(defaultVehicle);
    }

}
