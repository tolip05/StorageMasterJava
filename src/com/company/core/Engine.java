package com.company.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    private StorageMaster storageMaster;

    private boolean isRunning;

    public Engine(StorageMaster storageMaster) {
        this.storageMaster = storageMaster;
        this.isRunning = false;
    }
    BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    public void run() throws IOException {
        this.isRunning = true;

        while (isRunning){

            String line = br.readLine();
            String[] tokens = line.split("\\s+");



            String command = tokens[0];
            String output = "";

            try{
                String storageName;
                int garageSlot;
                switch (command){
                    case "AddProduct":
                        String productType = tokens[1];
                        double productPrice = Double.parseDouble(tokens[2]);
                        output = this.storageMaster.addProduct(productType, productPrice);
                        break;
                    case "RegisterStorage":
                        String storageType = tokens[1];
                        storageName = tokens[2];
                        output = this.storageMaster.registerStorage(storageType, storageName);
                        break;
                    case "SelectVehicle":
                        storageName = tokens[1];
                        garageSlot = Integer.parseInt(tokens[2]);
                        output = this.storageMaster.selectVehicle(storageName, garageSlot);
                        break;
                    case "LoadVehicle":
                        List<String> tokensList = new ArrayList<>();
                        for (int i = 1; i < tokens.length; i++) {
                            tokensList.add(tokens[i]);
                        }
                        output = this.storageMaster.loadVehicle(tokensList);
                        break;
                    case "SendVehicleTo":
                        String sourceName = tokens[1];
                        int sourceGarageSlot = Integer.parseInt(tokens[2]);
                        String destinationName = tokens[3];
                        output = this.storageMaster.sendVehicleTo(sourceName, sourceGarageSlot, destinationName);
                        break;
                    case "UnloadVehicle":
                        storageName = tokens[1];
                        garageSlot = Integer.parseInt(tokens[2]);
                        output = this.storageMaster.unloadVehicle(storageName, garageSlot);
                        break;
                    case "GetStorageStatus":
                        storageName = tokens[1];
                        output = this.storageMaster.GetStorageStatus(storageName);
                        break;
                    case "END":
                        output = this.storageMaster.getSummary();
                        this.isRunning = false;
                        break;
                }
            }catch (IllegalArgumentException e){
                output = String.format("Error: %s",e.getMessage());
            }
            System.out.println(output);
        }
    }
}
