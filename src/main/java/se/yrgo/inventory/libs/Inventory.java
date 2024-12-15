package se.yrgo.inventory.libs;

import java.util.HashMap;

public class Inventory {

    private HashMap<Product, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<Product, Integer>();
    }

    public void addProduct(Product p, int quantity) {
        inventory.put(p, inventory.get(p) + quantity);
    }

    public void removeProduct(Product p) {
        inventory.remove(p);
    }

    public int getProductInventory(Product p) {
        return inventory.get(p);
    }

    @Override
    public String toString() {
        return String.format("");
    }
}
