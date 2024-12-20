package se.yrgo.inventory.libs;

import java.util.ArrayList;

/**
 * This class represents an inventory, both for the software
 * and suppliers can have their own inventory to store their products.
 * Stores Products in a list.
 * @author joakimgidlund
 */
public class Inventory {

    private ArrayList<Product> inventory;

    public Inventory() {
        inventory = new ArrayList<Product>();
    }

    public ArrayList<Product> getInventory() {
        return inventory;
    }

    /**
     * Method to search for a product in the inventory by name to simplify input.
     * @param name name of the product
     * @return Product in the list if found
     * @throws ProductNotFoundException
     */
    public Product getProductByName(String name) throws ProductNotFoundException {
        for (Product p : inventory) {
            if (p.getType().equalsIgnoreCase(name)) {
                return p;
            }
        }
        throw new ProductNotFoundException("Product not found");
    }

    /**
     * Adds a product to the inventory list. If the same product by name
     * exists we add to the quantity of that product. Else we add the entire product
     * to inventory.
     * @param p The product we are trying to add.
     */
    public void addProduct(Product p) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).compareTo(p) == 0) {
                inventory.get(i).setQuantity(p.getQuantity() + p.getQuantity());
                return;
            }
        }
        inventory.add(p);
    }

    /**
     * Searches the inventory by product name
     * and removes it if the quantity removed is higher than stock.
     * Modifies quantity if more stock left.
     * @param pName name of product.
     * @param quantity quantity to remove.
     */
    public void removeProduct(String pName, int quantity) throws ProductNotFoundException {
        for(Product p : inventory) {
            if (p.getType().equalsIgnoreCase(pName)) {
                if(p.getQuantity() > quantity) {
                    p.setQuantity(p.getQuantity() - quantity);
                }
                else {
                    inventory.remove(p);
                }
                return;
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }

    /**
     * Prints the current inventory if it's not empty.
     */
    public void printInventory() {
        if (inventory.isEmpty()) {
            System.out.printf("Inventory empty.%n");
        } else {
            inventory.forEach(e -> System.out.printf("Product: %-10sQuantity: %d%n",
                    e.getType(), e.getQuantity()));
        }
    }

    /**
     * Different method for supplier inventories to change the output slightly
     * to also generate a visual list to select from.
     */
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
