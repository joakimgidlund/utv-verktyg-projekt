package se.yrgo.inventory.libs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Product product;

    public InventoryTest() throws ProductAlreadyExistsException {
        inventory = new Inventory();
        product = new Product(100, "Bok");
        inventory.addProduct(product, 1);
    }

    @Test
    public void testAddProduct() {
        assertNotNull(inventory, "Inventory not null after added product.");
        assertTrue(inventory.getInventory().containsKey(product),
                "Checking product that product exists in inventory.");

        assertEquals(1, inventory.getProductInventory(product),
                "Checking correct quantity added.");

        assertThrows(ProductAlreadyExistsException.class, () ->
            inventory.addProduct(product, 1),
                "Testing ProductAlreadyExistsException.");
    }

    @Test
    public void testRemoveProduct() {
        Inventory tempInventory = inventory;
        tempInventory.removeProduct(product);
        assertFalse(tempInventory.getInventory().containsKey(product),
                "Inventory doesn't contain product after removal.");
    }

    @Test
    public void testGetProductInventory() {
        assertEquals(1, inventory.getProductInventory(product),
                "Product quantity is correct.");
    }

    @Test
    public void testAddProductInventory() throws ProductNotFoundException {
        inventory.addProductInventory(product, 1);
        assertEquals(2, inventory.getProductInventory(product));

        Product testProduct = new Product(50, "Test");
        assertThrows(ProductNotFoundException.class, () ->
                inventory.addProductInventory(testProduct, 2),
                "Testing ProductNotFoundException.");
    }
}
