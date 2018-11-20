package com.company.factories;

import com.company.products.*;

public class ProductFactory {
    public Product createProduct(String type,double price){
        int defaultWeight = 0;
        Product product = null;
        switch (type){
            case "Ram":
                product = new Ram(price,defaultWeight);
                break;
            case "Gpu":
                product = new Gpu(price,defaultWeight);
                break;
            case "HardDrive":
                product = new HardDrive(price,defaultWeight);
                break;
            case "SolidStateDrive":
                product = new SolidStateDrive(price,defaultWeight);
                break;
            default:
                throw new IllegalArgumentException("Invalid product type!");

        }
        return product;
    }
}
