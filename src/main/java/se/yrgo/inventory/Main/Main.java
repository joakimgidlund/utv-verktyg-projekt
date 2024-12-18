package se.yrgo.inventory.Main;

import se.yrgo.inventory.libs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static ArrayList<Supplier> suppliers;

    public static void main(String[] args) {
        boolean appLoop = true;
        Inventory inventory = new Inventory();
        Inventory axfoodInv = populateAxfood();
        Inventory ahlsellInv = populateAhlsell();

        Supplier axfood = new Supplier("Axfood",
                "Solnavägen 4, 113 65 Stockholm",
                "08-553 99 000", axfoodInv);
        Supplier ahlsell = new Supplier("Ahlsell Sverige AB",
                "Årstaängsvägen 17, 117 43 Stockholm",
                "08-685 70 00", ahlsellInv);

        suppliers = new ArrayList<>();
        suppliers.add(axfood);
        suppliers.add(ahlsell);

        System.out.printf("Welcome to XYZ inventory management services.%n%n");
        try (Scanner scan = new Scanner(System.in)) {
            while (appLoop) {
                System.out.printf("Make your selection below:%n");
                System.out.printf("1. Add product to inventory%n" +
                        "2. Remove product from inventory%n" +
                        "3. View current inventory%n" +
                        "4. List suppliers%n" +
                        "0. Exit%n");

                int choice = scan.nextInt();
                switch (choice) {
                    case 1 -> addProduct(scan, inventory);
                    case 2 -> removeProduct(scan, inventory);
                    case 3 -> listInventory(inventory);
                    case 4 -> listSuppliers();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        appLoop = false;
                    }
                    default -> System.out.println("Invalid selection");
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void addProduct(Scanner scan, Inventory inventory) {
        System.out.printf("Each supplier carries product, choose which supplier to buy from.%n");
        listSuppliers();

        System.out.print("Select supplier: ");
        int choice = scan.nextInt() - 1;
        System.out.print("Product number: ");
        int productNr = scan.nextInt() - 1;
        System.out.println("Quantity to purchase: ");
        int quantity = scan.nextInt();

        boolean forRemoval = false;
        try {
            if(choice > suppliers.size() - 1) {
                throw new IndexOutOfBoundsException("That supplier doesn't exist.");
            }
            List<Product> list = suppliers.get(choice).getInventory().getInventory();

            if(productNr > list.size() - 1) {
                throw new IndexOutOfBoundsException("That product doesn't exist.");
            }
            Product insert = list.get(productNr);
            if (insert.getQuantity() < quantity) {
                System.out.println("Not enough in inventory, purchasing all.");
                quantity = insert.getQuantity();
                forRemoval = true;
            }
            inventory.addProduct(new Product(insert.getPrice(), insert.getType(), quantity));
            suppliers.get(choice).getInventory()
                    .getInventory()
                    .get(productNr)
                    .setQuantity(insert.getQuantity() - quantity);

            if (forRemoval) {
                suppliers.get(choice - 1).getInventory().removeProduct(list.get(productNr - 1).getType());
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void removeProduct(Scanner scan, Inventory i) {
        i.printInventory();

        scan.nextLine(); //move scanner to next line.

        if (!i.getInventory().isEmpty()) {
            System.out.print("Type product name to remove: ");
            String pName = scan.nextLine();
            System.out.print("Quantity to remove: ");
            int quantity = scan.nextInt();
            try {
                if (i.getProductByName(pName) == null) {
                    System.out.printf("Product does not exist.%n%n");
                    return;
                }
                if (i.getProductByName(pName).getQuantity() < quantity) {
                    i.removeProduct(pName);
                    System.out.printf("Product removed from inventory.%n%n");
                } else {
                    i.getProductByName(pName).setQuantity(i.getProductByName(pName).getQuantity() - quantity);
                }
            } catch (ProductNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private static void listInventory(Inventory i) {
        i.printInventory();
    }

    private static void listSuppliers() {
        int suppCount = 1;
        for (Supplier s : suppliers) {
            System.out.printf("%d: %s%n----------------------------%n", suppCount, s.getName());
            s.getInventory().printSupplierInventory();
            System.out.println("----------------------------");
            suppCount++;
        }
    }

    private static Inventory populateAhlsell() {
        Inventory inventory = new Inventory();
        inventory.addProduct(new Product(1, "Nail", 10500));
        inventory.addProduct(new Product(2, "Nut", 1000));
        inventory.addProduct(new Product(150, "Hammer", 2000));
        inventory.addProduct(new Product(1999, "Work pants", 100));
        return inventory;
    }

    private static Inventory populateAxfood() {
        Inventory inventory = new Inventory();
        inventory.addProduct(new Product(3, "Apple", 450));
        inventory.addProduct(new Product(5, "Banana", 1000));
        inventory.addProduct(new Product(6, "Orange", 2000));
        inventory.addProduct(new Product(10, "Cinnamon", 100));
        return inventory;
    }
}
