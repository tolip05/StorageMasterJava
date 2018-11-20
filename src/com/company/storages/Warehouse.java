package com.company.storages;

import com.company.vehicles.Semi;
import com.company.vehicles.Vehicle;

import java.util.Arrays;
import java.util.List;

public class Warehouse extends Storage {
    private final int WAREHOUSE_CAPACITY = 10;
    private final int WAREHOUSE_GARAGE_SLOTS = 10;
    private static Vehicle[] defaultVehicle=
            {
                    new Semi(0),
                    new Semi(0),
                    new Semi(0),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            };
    public Warehouse(String name, int capacity, int garageSlots, List<Vehicle> vehicles) {
        super(name, capacity, garageSlots, vehicles);
        super.setCapacity(WAREHOUSE_CAPACITY);
        super.setGarageSlots(WAREHOUSE_GARAGE_SLOTS);
        super.setGarage(defaultVehicle);
    }



}
