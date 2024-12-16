package se.yrgo.inventory.libs;

import java.util.HashMap;

public class Inventory {

    private HashMap<Product, Integer> inventoryMap;

    public Inventory() {
        inventoryMap = new HashMap<Product, Integer>();
    }

    public HashMap<Product, Integer> getInventory() {
        return inventoryMap;
    }

    public void addProduct(Product p, int quantity) throws ProductAlreadyExistsException {
        if(inventoryMap.entrySet().stream().noneMatch(e -> e.getKey().getType().equals(p.getType()))) {
            inventoryMap.put(p, quantity);
        }
        else {
            throw new ProductAlreadyExistsException("Product already in inventory.");
        }
    }

    public void removeProduct(String pName) {
        inventoryMap.entrySet().removeIf(e -> e.getKey().getType().toLowerCase().equals(pName.toLowerCase()));
    }

    public int getProductInventory(Product p) {
        return inventoryMap.get(p);
    }

    public void addProductInventory(Product p, int quantity) throws ProductNotFoundException {
        if(inventoryMap.containsKey(p)) {
            inventoryMap.put(p, inventoryMap.get(p) + quantity);
        }
        else {
            throw new ProductNotFoundException("No such product in inventory.");
        }
    }

    public void printInventory() {
        if(inventoryMap.isEmpty()) {
            System.out.println("Inventory empty.");
        }
        else {
            inventoryMap.entrySet()
                    .forEach(e -> System.out.printf("Product: %-10sQuantity: %d%n", e.getKey().getType(), e.getValue()));
        }
    }
}
