package se.yrgo.inventory.libs;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Product> inventory;

    public Inventory() {
        inventory = new ArrayList<Product>();
    }

    public ArrayList<Product> getInventory() {
        return inventory;
    }

    public Product getProductByName(String name) throws ProductNotFoundException {
        for (Product p : inventory) {
            if (p.getType().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    public void addProduct(Product p) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).compareTo(p) == 0) {
                inventory.get(i).setQuantity(p.getQuantity() + p.getQuantity());
                return;
            }
        }
        inventory.add(p);
    }

    public void removeProduct(String pName) {
        inventory.removeIf(p -> p.getType().equalsIgnoreCase(pName));
    }

    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.printf("Inventory empty.%n%n");
        } else {
            inventory.forEach(e -> System.out.printf("Product: %-10sQuantity: %d%n",
                    e.getType(), e.getQuantity()));
        }
    }

    public void printSupplierInventory() {
        int count = 1;
        if (inventory.isEmpty()) {
            System.out.printf("Inventory empty.%n%n");
        } else {
            for (Product p : inventory) {
                System.out.printf("%d: %-20sQuantity: %d%n",
                        count, p.getType(), p.getQuantity());
                count++;
            }
        }
    }
}
