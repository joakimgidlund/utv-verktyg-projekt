package se.yrgo.inventory.Main;

import se.yrgo.inventory.libs.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Inventory management software. Handles a set of suppliers and
 * allows purchasing to your inventory and removing products.
 *
 * @author joakimgidlund
 */
public class Main {

    public static ArrayList<Supplier> suppliers;

    /**
     * Main shows the main menu and lets the user
     * select an option.
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean appLoop = true;
        Inventory inventory = new Inventory();
        Inventory axfoodInv = populateAxfood();
        Inventory ahlsellInv = populateAhlsell();

        Supplier axfood = new Supplier("Axfood",
                "Solnavägen 4, 113 65 Stockholm",
                "08-553 99 00", axfoodInv);
        Supplier ahlsell = new Supplier("Ahlsell Sverige AB",
                "Årstaängsvägen 17, 117 43 Stockholm",
                "08-685 70 00", ahlsellInv);

        suppliers = new ArrayList<>();
        suppliers.add(axfood);
        suppliers.add(ahlsell);

        System.out.printf("Welcome to XYZ inventory management services.%n%n");
        try {
            while (appLoop) {
                System.out.printf("Make your selection below:%n");
                System.out.printf("1. Add product to inventory%n" +
                        "2. Remove product from inventory%n" +
                        "3. View current inventory%n" +
                        "4. List suppliers%n" +
                        "5. View supplier information%n" +
                        "0. Exit%n");

                try {
                    Scanner scan = new Scanner(System.in);
                    int choice = scan.nextInt();
                    switch (choice) {
                        case 1 -> addProduct(scan, inventory);
                        case 2 -> removeProduct(scan, inventory);
                        case 3 -> listInventory(inventory);
                        case 4 -> listSuppliers();
                        case 5 -> printSupplierInfo(suppliers);
                        case 0 -> {
                            System.out.println("Goodbye!");
                            appLoop = false;
                        }
                        default -> System.out.println("Invalid input. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Try again");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method handles adding products to the specified inventory.
     * It's written to handle expansion of adding new products
     * to a supplier's inventory in the "future".
     * Handles bad inputs and returns to the main menu.
     *
     * @param scan      Scanner
     * @param inventory the user's inventory.
     */
    private static void addProduct(Scanner scan, Inventory inventory) {
        System.out.printf("Each supplier carries product, choose which supplier to buy from.%n");
        listSuppliers();


        System.out.print("Select supplier: ");
        int choice = scan.nextInt() - 1;
        if (choice > suppliers.size() - 1 || choice < 0) {
            System.out.println("That supplier doesn't exist.");
            return;
        }

        List<Product> list = suppliers.get(choice).getInventory().getInventory();

        System.out.print("Product number: ");
        int productNr = scan.nextInt() - 1;
        if (productNr > list.size() - 1 || productNr < 0) {
            System.out.println("That product doesn't exist.");
            return;
        }

        System.out.print("Quantity to purchase: ");
        int quantity = scan.nextInt();

        //Temporary product to add later
        Product insert = list.get(productNr);

        //If quantity selected is too high we remove from supplier inventory.
        if (insert.getQuantity() <= quantity) {
            System.out.println("Not enough in inventory, purchasing all.");
            quantity = insert.getQuantity();
        }

        inventory.addProduct(new Product(insert.getPrice(), insert.getType(), quantity));

        try {
            suppliers.get(choice).getInventory().removeProduct(insert.getType(), quantity);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    /**
     * Handles the removal of a product.
     * First prints and then lets the user select the product
     * by name. Find the product by product name and then removes it
     * from the inventory if quantity removed is higher than available stock.
     *
     * @param scan
     * @param i    the user's inventory
     */
    private static void removeProduct(Scanner scan, Inventory i) {
        i.printInventory();

        scan.nextLine(); //move scanner to next line.

        if (!i.getInventory().isEmpty()) {
            try {
                System.out.print("Type product name to remove: ");
                String pName = scan.nextLine();
                Product product = i.getProductByName(pName);
                if (product == null) {
                    System.out.println("Product not found.");
                    return;
                }
                System.out.print("Quantity to remove: ");
                int quantity = scan.nextInt();

                i.removeProduct(pName, quantity);
            } catch (ProductNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     * Lists the provided inventory.
     *
     * @param i
     */
    private static void listInventory(Inventory i) {
        i.printInventory();
        System.out.println();
    }

    /**
     * Calls printSupplierInventory to list their inventory,
     * but also adds some formatting for readability.
     */
    private static void listSuppliers() {
        int suppCount = 1;
        for (Supplier s : suppliers) {
            System.out.printf("%d: %s%n----------------------------%n", suppCount, s.getName());
            s.getInventory().printSupplierInventory();
            System.out.println("----------------------------");
            suppCount++;
        }
        System.out.println();
    }

    /**
     * Prints the raw data about suppliers.
     *
     * @param suppliers
     */
    private static void printSupplierInfo(List<Supplier> suppliers) {
        for (Supplier s : suppliers) {
            System.out.println(s.toString());
        }
        System.out.println();
    }

    /**
     * Method to fill supplier inventory with test data.
     */
    private static Inventory populateAhlsell() {
        Inventory inventory = new Inventory();
        inventory.addProduct(new Product(1, "Nail", 10500));
        inventory.addProduct(new Product(2, "Nut", 1000));
        inventory.addProduct(new Product(150, "Hammer", 2000));
        inventory.addProduct(new Product(1999, "Work pants", 100));
        return inventory;
    }

    /**
     * Method to fill supplier inventory with test data.
     */
    private static Inventory populateAxfood() {
        Inventory inventory = new Inventory();
        inventory.addProduct(new Product(3, "Apple", 450));
        inventory.addProduct(new Product(5, "Banana", 1000));
        inventory.addProduct(new Product(6, "Orange", 2000));
        inventory.addProduct(new Product(10, "Cinnamon", 100));
        return inventory;
    }
}
