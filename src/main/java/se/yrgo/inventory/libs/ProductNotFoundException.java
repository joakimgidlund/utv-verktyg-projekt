package se.yrgo.inventory.libs;

public class ProductNotFoundException extends Exception {
    private static final long serialVersionUID = 12311L;

    public ProductNotFoundException(String s) {
        super(s);
    }
}
