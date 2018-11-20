package com.company.vehicles;

import com.company.products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Vehicle {
    private int capacity;

    private List<Product>trunk;

    protected Vehicle(int capacity) {
        this.capacity = capacity;
        this.trunk = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Product> getTrunk() {
        return this.trunk;
    }

    private void setTrunk(List<Product> products) {
        this.trunk = products;
    }
    public boolean isFull(){
        return this.getTrunk().size() >= this.getCapacity();
    }
    public boolean isEmpty(){
        return this.getTrunk().size() == 0;
    }
    public void loadProduct(Product product){
        if (isFull()){
            throw new IllegalArgumentException("Vehicle is full!");
        }
        this.trunk.add(product);
    }
    public Product unLoad(){
        if (isEmpty()){
            throw new IllegalArgumentException("No products left in vehicle!");
        }
        Product product = this.getTrunk().get(this.trunk.size() - 1);
        this.getTrunk().remove(this.getTrunk().size() - 1);
        return product;
    }
}
