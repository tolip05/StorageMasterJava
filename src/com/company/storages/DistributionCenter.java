package com.company.storages;

import com.company.vehicles.Van;
import com.company.vehicles.Vehicle;

import java.util.List;

public class DistributionCenter extends Storage {
    private final int DISTRIBUTIONCENTER_CAPACOTY = 2;
    private final int DISTRIBUTIONCENTER_GARAGE_SLOTS = 5;
    private static Vehicle[] defaultVehicle = new Vehicle[]
            {
                    new Van(0),
                    new Van(0),
                    new Van(0),
                null,
                null
            };

    public DistributionCenter(String name, int capacity, int garageSlots, List<Vehicle> vehicles) {
        super(name, capacity, garageSlots, vehicles);
        super.setCapacity(DISTRIBUTIONCENTER_CAPACOTY);
        super.setGarageSlots(DISTRIBUTIONCENTER_GARAGE_SLOTS);
        super.setGarage(defaultVehicle);
    }

}
