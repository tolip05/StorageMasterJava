package com.company.factories;

import com.company.storages.AutomatedWarehouse;
import com.company.storages.DistributionCenter;
import com.company.storages.Storage;
import com.company.storages.Warehouse;
import com.company.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class StorageFactory {
    public Storage createStorage(String type,String name){
        Storage storage = null;
        int capacity = 0;
        int garageSlot = 0;
        List<Vehicle>vehicles = new ArrayList<>();
        switch (type){
            case "AutomatedWarehouse":
                storage = new AutomatedWarehouse(name,capacity,garageSlot,vehicles);
                break;
            case "DistributionCenter":
                storage = new DistributionCenter(name,capacity,garageSlot,vehicles);
                break;
            case "Warehouse":
                storage = new Warehouse(name,capacity,garageSlot,vehicles);
                break;
                default:
                    throw new IllegalArgumentException("Invalid storage type!");
        }
        return storage;
    }
}
