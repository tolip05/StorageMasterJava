package com.company.storages;

import com.company.products.Product;
import com.company.vehicles.Vehicle;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Storage {
    private String name;
    private int capacity;
    private int garageSlots;
    private List<Product>products;
    private Vehicle[] garage;

    protected Storage(String name, int capacity, int garageSlots, List<Vehicle> vehicles) {
        this.setName(name);
        this.setCapacity(capacity);
        this.setGarageSlots(garageSlots);
        this.products = new ArrayList<>();
        this.garage = new Vehicle[this.getGarageSlots()];
        this.fillGarageWithInitialVehicles(vehicles);
    }

    private void fillGarageWithInitialVehicles(List<Vehicle> vehicles) {
        int index = 0;
        this.garage = new Vehicle[this.getGarageSlots()];
        for (Vehicle vehicle : vehicles) {
            this.garage[index] = vehicle;
            index++;
        }
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getGarageSlots() {
        return this.garageSlots;
    }

    protected void setGarageSlots(int garageSlots) {
        this.garageSlots = garageSlots;
    }

    protected void setProducts(List<Product> products) {
        this.products = products;
    }

    protected void setGarage(Vehicle[] garage)
    {
        this.garage = garage;
    }
    protected Vehicle[] setGarageChild(Vehicle[] vehicle){
      int capacity = this.getGarage().length;
        for (int i = 0; i < capacity; i++) {
            if (vehicle[i] != null) {
                this.getGarage()[i] = vehicle[i];
            }
        }
        return this.getGarage();
    }

    public boolean isFull(){
        int sum = 0;
        for (Product product : this.getProducts()) {
            sum += product.getWeight();
        }
        return sum >= this.getCapacity();
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Vehicle[] getGarage() {
        return this.garage;
    }
    public Vehicle getVehicle(int garageSlot){
        if (garageSlot >= this.getGarageSlots()){
            throw new IllegalArgumentException("Invalid garage slot!");
        }
        Vehicle vehicle = this.getGarage()[garageSlot];
        if (vehicle == null){
            throw new IllegalArgumentException("No vehicle in this garage slot!");
        }
         return vehicle;
    }
  public int sendVehicleTo(int garageSlot, Storage deliveryLocation){
        Vehicle vehicle = this.getVehicle(garageSlot);
        int foundGarageSlotIndex = deliveryLocation.addVehicleToGarage(vehicle);
        this.getGarage()[garageSlot] = null;
        return foundGarageSlotIndex;
  }
  public int unloadVehicle(int garageSlot){
   if (this.isFull()){
       throw new IllegalArgumentException("Storage is full!");
   }
   Vehicle vehicle = this.getVehicle(garageSlot);
      int unloadedProductsCounter = 0;
      while (!this.isFull() && !vehicle.isEmpty()){
          Product product = vehicle.unLoad();
          this.getProducts().add(product);
          unloadedProductsCounter++;
      }
      return unloadedProductsCounter;
  }


    private int addVehicleToGarage(Vehicle vehicle) {

        int freeGarageSlotIndex = -1;
        for (int i = 0; i < this.getGarage().length; i++) {
            if (this.garage[i] == null){
                freeGarageSlotIndex = i;
                break;
            }
        }
        if (freeGarageSlotIndex == -1){
            throw new IllegalArgumentException("No room in garage!");

        }

        this.getGarage()[freeGarageSlotIndex] = vehicle;
        return freeGarageSlotIndex;
    }

}
