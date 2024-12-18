package se.yrgo.inventory.libs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(100, "Bok", 1);
    }

    @Test
    public void testCreateProduct() {
        // Kontrollera att alla attribut är korrekt inställda
        assertEquals(100, product.getPrice(), "Product price should be correctly set.");
        assertEquals("Bok", product.getType(), "Product type should be correctly set.");
        assertEquals(1, product.getQuantity(), "Product quantity should be correctly set.");
    }

    @Test
    public void testSetQuantity() {
        // Ändra kvantiteten
        product.setQuantity(5);

        // Kontrollera att kvantiteten uppdateras korrekt
        assertEquals(5, product.getQuantity(), "Product quantity should update correctly.");
    }

    @Test
    public void testSetPrice() {
        // Ändra priset
        product.setPrice(150);

        // Kontrollera att priset uppdateras korrekt
        assertEquals(150, product.getPrice(), "Product price should update correctly.");
    }

    @Test
    public void testSetType() {
        // Ändra typen
        product.setType("Penna");

        // Kontrollera att typen uppdateras korrekt
        assertEquals("Penna", product.getType(), "Product type should update correctly.");
    }

    @Test
    public void testEqualsAndHashCode() {
        // Skapa en annan produkt med samma värden
        Product sameProduct = new Product(100, "Bok", 1);
        Product differentProduct = new Product(200, "Penna", 2);

        // Kontrollera att produkterna anses lika
        assertEquals(product, sameProduct, "Products with same values should be equal.");
        assertEquals(product.hashCode(), sameProduct.hashCode(),
                "Products with same values should have the same hash code.");

        // Kontrollera att olika produkter inte anses lika
        assertNotEquals(product, differentProduct, "Products with different values should not be equal.");
    }

    @Test
    public void testCompareTo() {
        // Skapa andra produkter för jämförelse
        Product anotherProduct = new Product(150, "Penna", 1);

        // Kontrollera jämförelsen baserat på `type`
        assertTrue(product.compareTo(anotherProduct) < 0,
                "Product with type 'Bok' should come before 'Penna'.");
        assertTrue(anotherProduct.compareTo(product) > 0,
                "Product with type 'Penna' should come after 'Bok'.");

        // Jämförelse med samma typ
        Product sameTypeProduct = new Product(200, "Bok", 2);
        assertEquals(0, product.compareTo(sameTypeProduct),
                "Products with the same type should be equal in comparison.");
    }
}
