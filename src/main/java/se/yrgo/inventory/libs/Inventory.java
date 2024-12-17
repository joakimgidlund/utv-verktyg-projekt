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

    public void addProduct(Product p) throws ProductAlreadyExistsException {
        for (Product prod : inventory) {
            if (prod.compareTo(p) == 0) {
                throw new ProductAlreadyExistsException("Product already exists.");
            }
        }
        inventory.add(p);

    }

    public void removeProduct(String pName) {
        inventory.removeIf(p -> p.getType().equalsIgnoreCase(pName));
    }

    public int getProductInventory(Product p) {
        for (Product prod : inventory) {
            if (prod.equals(p)) {
                return prod.getQuantity();
            }
        }
        return -1;
    }

    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory empty.");
        } else {
            inventory.stream()
                    .forEach(e -> System.out.printf("Product: %-10sQuantity: %d%n",
                            e.getType(), e.getQuantity()));
        }
    }
}
