package se.yrgo.inventory.Main;

import se.yrgo.inventory.libs.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean appLoop = true;
        Inventory inventory = new Inventory();

        System.out.printf("Welcome to XYZ inventory management services.%n%n");
        try(Scanner scan = new Scanner(System.in)) {
        while(appLoop){
            System.out.printf("Make your selection below:%n");
            System.out.printf("1. Add product to inventory%n" +
                    "2. Remove product from inventory%n" +
                    "3. View current inventory of a product%n" +
                    "4. List suppliers%n" +
                    "0. Exit%n");

                int choice = scan.nextInt();
                switch(choice) {
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
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void addProduct(Scanner scan, Inventory inventory) {
        System.out.printf("Each supplier carries product, choose which supplier to buy from.%n");
        int count = 1;
        /*for(Supplier s : suppliers) {
            System.out.printf("%d: %s", count, s.getName());
            count++;
        }*/

        System.out.print("Input selection: ");
        int choice = scan.nextInt();
        System.out.print("Quantity to add: ");
        int quantity = scan.nextInt();
        try {
            inventory.addProduct(new Product(100, "Juice"), quantity);
            inventory.addProduct(new Product(100, "Potatis"), quantity);
            inventory.addProduct(new Product(100, "Banan"), quantity);
            inventory.addProduct(new Product(100, "Äpple"), quantity);
        } catch (ProductAlreadyExistsException ex) {
            System.err.println(ex.getMessage());
            System.out.println();
        }
    }
    private static void removeProduct(Scanner scan, Inventory i) {
        i.printInventory();

        scan.nextLine(); //move scanner to next line.

        if(!i.getInventory().isEmpty()) {
            System.out.print("Type product name to remove: ");
            String pName = scan.nextLine();
            i.removeProduct(pName);
        }

    }
    private static void listInventory(Inventory i) {
        i.printInventory();
    }
    private static void listSuppliers() {
        /*for(Supplier s : suppliers) {
            System.out.println(s.getName());
        }*/
    }



}
