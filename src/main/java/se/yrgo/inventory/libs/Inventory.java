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
        if(!inventoryMap.containsKey(p)) {
            inventoryMap.put(p, quantity);
        }
        else {
            throw new ProductAlreadyExistsException("Product already in inventory.");
        }
    }

    public void removeProduct(Product p) {
        inventoryMap.remove(p);
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
    @Override
    public String toString() {
        return String.format("");
    }
}
