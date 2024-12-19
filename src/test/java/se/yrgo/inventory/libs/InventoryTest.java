package se.yrgo.inventory.libs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Product product;

    public InventoryTest() {
        inventory = new Inventory();
        product = new Product(100, "Bok", 1);
        inventory.addProduct(product);
    }

    @Test
    public void testAddProduct() throws ProductNotFoundException {
        assertNotNull(inventory, "Inventory not null after added product.");
        assertTrue(inventory.getInventory().contains(product),
                "Checking product that product exists in inventory.");

        assertEquals(1, inventory.getProductByName(product.getType()).getQuantity(),
                "Checking correct quantity added.");
    }

    @Test
    public void testRemoveProduct() {
        Inventory tempInventory = inventory;
        tempInventory.removeProduct("Bok");
        assertFalse(tempInventory.getInventory().contains(product),
                "Inventory doesn't contain product after removal.");
    }

    @Test
    public void testGetProductByName() throws ProductNotFoundException {
        assertEquals(inventory.getProductByName(product.getType()), product,
                "Finding the correct product by searching.");
        assertThrows(ProductNotFoundException.class, () -> {
            inventory.getProductByName("Test product");
        });
    }
}
