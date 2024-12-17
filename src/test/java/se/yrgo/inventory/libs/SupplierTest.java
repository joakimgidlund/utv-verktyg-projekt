package se.yrgo.inventory.libs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SupplierTest {

    @Test
    public void testSupplierConstructor() {
        Supplier supplier = new Supplier("ABC Supplies", "123 Street, City", "012-3456789");

        assertNotNull(supplier, "Supplier should not be null after creation.");
        assertEquals("ABC Supplies", supplier.getName(), "Supplier name should be 'ABC Supplies'.");
        assertEquals("123 Street, City", supplier.getAddress(), "Supplier address should be '123 Street, City'.");
        assertEquals("012-3456789", supplier.getPhone(), "Supplier phone number should be '012-3456789'.");
    }

    @Test
    public void testSettersAndGetters() {
        Supplier supplier = new Supplier("XYZ Traders", "456 Avenue, Town", "987-6543210");

        supplier.setName("New Supplier");
        supplier.setAddress("789 New Road, New City");
        supplier.setPhone("111-222333");

        assertEquals("New Supplier", supplier.getName(), "Supplier name should be 'New Supplier'.");
        assertEquals("789 New Road, New City", supplier.getAddress(), "Supplier address should be '789 New Road, New City'.");
        assertEquals("111-222333", supplier.getPhone(), "Supplier phone number should be '111-222333'.");
    }

    @Test
    public void testToString() {
        Supplier supplier = new Supplier("XYZ Traders", "456 Avenue, Town", "987-6543210");
        String expected = "Supplier{name='XYZ Traders', address='456 Avenue, Town', phone='987-6543210'}";
        assertEquals(expected, supplier.toString(), "The toString() method should return the expected string.");
    }
}
