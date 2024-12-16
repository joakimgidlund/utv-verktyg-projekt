package se.yrgo.inventory.libs;

public class ProductAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 9123L;

    public ProductAlreadyExistsException(String s) {
        super(s);
    }
}
