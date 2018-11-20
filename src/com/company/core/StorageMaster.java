package com.company.core;

import com.company.factories.ProductFactory;
import com.company.factories.StorageFactory;
import com.company.products.Product;
import com.company.storages.Storage;
import com.company.vehicles.Vehicle;

import java.awt.font.TextHitInfo;
import java.util.*;
import java.util.stream.Collectors;

public class StorageMaster {
    private ProductFactory productFactory;
    private StorageFactory storageFactory;
    private Map<String, ArrayDeque<Product>> products;
    private Map<String, Storage> storages;
    private Vehicle currentVehicle;

    public StorageMaster() {
        this.products = new HashMap<>();
        this.storages = new HashMap<>();
        this.productFactory = new ProductFactory();
        this.storageFactory = new StorageFactory();
    }

    public String addProduct(String type, double price) {

        Product product = this.productFactory.createProduct(type, price);
        this.products.putIfAbsent(type, new ArrayDeque<>());
        this.products.get(type).push(product);
        String result = String.format("Added %s to pool", product.getClass().getSimpleName());
        return result;
    }

    public String registerStorage(String type, String name) {

        Storage storage = this.storageFactory.createStorage(type, name);
        this.storages.put(name, storage);
        String result = String.format("Registered %s", name);
        return result;
    }

    public String selectVehicle(String storageName, int garageSlot) {

        Storage storage = this.storages.get(storageName);
        this.currentVehicle = storage.getVehicle(garageSlot);
        String result = String.format("Selected %s", currentVehicle.getClass().getSimpleName());
        return result;
    }

    public String loadVehicle(List<String> productNames) {
        int loadedProductsCount = 0;
        for (String productName : productNames) {
            if (this.currentVehicle.isFull()) {
                break;
            }
            if (!this.products.containsKey(productName)
                    || this.products.get(productName).size() == 0) {
                throw new IllegalArgumentException(String.format("%s is out of stock!", productName));
            }
            Product product = this.products.get(productName).pop();
            this.currentVehicle.loadProduct(product);
            loadedProductsCount++;
        }
        String result = String.format("Loaded %d/%d products into %s"
                , loadedProductsCount, productNames.size(), this.currentVehicle.getClass().getSimpleName());
        return result;
    }

    public String sendVehicleTo(String sourceName, int sourceGarageSlot, String destinationName) {
        if (!this.storages.containsKey(sourceName)) {
            throw new IllegalArgumentException("Invalid source storage!");
        }
        if (!this.storages.containsKey(destinationName)) {
            throw new IllegalArgumentException("Invalid destination storage!");

        }
        Storage sourceStorage = this.storages.get(sourceName);
        Storage destinationStorage = this.storages.get(destinationName);
        Vehicle vehicle = sourceStorage.getVehicle(sourceGarageSlot);

        int destinationGarageSlot = sourceStorage.sendVehicleTo(sourceGarageSlot, destinationStorage);
        String result = String.format("Sent %s to %s (slot %d)", vehicle.getClass().getSimpleName()
                , destinationName, destinationGarageSlot);
        return result;
    }

    public String unloadVehicle(String storageName, int garageSlot) {

        Storage storage = this.storages.get(storageName);
        int countProductsInVehicle = storage.getVehicle(garageSlot).getTrunk().size();
        int unloadedProductsCounter = storage.unloadVehicle(garageSlot);
        String result = String.format("Unloaded %d/%d products at %s"
                , countProductsInVehicle, unloadedProductsCounter, storageName);
        return result;
    }

    public String GetStorageStatus(String storageName) {
        Storage storage = this.storages.get(storageName);
        Map<String, Integer> productsAndCount = new HashMap<>();
        for (Product product : storage.getProducts()) {
            String productTypeName = product.getClass().getSimpleName();
            if (!productsAndCount.containsKey(productTypeName)) {
                productsAndCount.put(productTypeName, 1);
            } else {
                int nums = productsAndCount.get(productTypeName);
                nums++;
                productsAndCount.put(productTypeName, nums);
            }
        }
        double productSum = 0;
        for (Product product : storage.getProducts()) {
            productSum += product.getWeight();
        }
        int storageCapacity = storage.getCapacity();

        Map<String, Integer> sortedProducts = productsAndCount.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        String[] productsAsString = new String[sortedProducts.size()];
        int index = 0;
        for (Map.Entry<String, Integer> product : sortedProducts.entrySet()) {
            String currentResult = String.format("%s (%s)",product.getKey()
            ,product.getValue().toString());
            productsAsString[index++] = currentResult;
        }

       String stockLine = String.format("Stock(%.2f/%d): [%s}]"
       ,productSum,storageCapacity,String.join(", ",productsAsString));

        String[]storageStringRepresentive = new String[storage.getGarageSlots()];
        int indexVehicle = 0;
        for (Vehicle vehicle : storage.getGarage()) {
            if (vehicle == null){
                storageStringRepresentive[indexVehicle++] = "empty";
            }else{
                storageStringRepresentive[indexVehicle++] = vehicle.getClass().getSimpleName();
            }
        }
        String lineResult = String.format("Garage: [%s]"
        ,String.join("\\|",storageStringRepresentive));
        String result = stockLine + "\n"
                + lineResult;
        return result;
    }
    public String getSummary(){
        List<Storage>sortedStorages = new ArrayList<>();

        sortedStorages.addAll(storages.values());
        Map<Integer,Storage>mapSort = new HashMap<>();
        for (Storage storage : sortedStorages) {
            int sum = 0;
            for (Storage value : storages.values()) {
                for (ArrayDeque<Product> deque : products.values()) {
                    for (Product product : deque) {
                        sum += product.getPrice();
                    }
                }
            }
            if (!mapSort.containsValue(storage)){
                mapSort.put(sum,storage);
            }
        }
        LinkedHashMap<Integer,Storage> sortedMap = new LinkedHashMap<>();
                mapSort.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
            sortedStorages.clear();
      //  sortedStorages.addAll(sortedMap.values());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Storage> entry : sortedMap.entrySet()) {
            double totalMoney = entry.getKey();
            String firstLine = String.format("%s:",entry.getValue().getName());
            String secondLine = String.format("Storage worth: $%.2f",totalMoney);
            sb.append(firstLine).append(System.lineSeparator())
                    .append(secondLine);
        }

        return sb.toString();
    }


}
