package se.yrgo.inventory.libs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Inventory inventory;
    private Product product;

    @BeforeEach
    public void setUp() throws ProductAlreadyExistsException {
        inventory = new Inventory();
        product = new Product(100, "Bok", 1);
        inventory.addProduct(product);
    }

    @Test
    public void testAddProduct() {
        // Kontrollera att inventariet inte är null
        assertNotNull(inventory, "Inventory should not be null after initialization.");

        // Kontrollera att produkten finns i inventariet
        assertTrue(inventory.getInventory().contains(product),
                "Product should exist in inventory after being added.");

        // Kontrollera att kvantiteten av produkten är korrekt
        assertEquals(1, product.getQuantity(),
                "Product quantity should match the added quantity.");
    }

    @Test
    public void testRemoveProduct() {
        // Ta bort produkten från inventariet
        inventory.removeProduct("Bok");

        // Kontrollera att produkten inte finns i inventariet
        assertFalse(inventory.getInventory().contains(product),
                "Product should not exist in inventory after removal.");
    }

    @Test
    public void testGetProductInventory() {
        // Kontrollera kvantiteten av produkten
        assertEquals(1, product.getQuantity(),
                "Product quantity should be correct.");
    }
}