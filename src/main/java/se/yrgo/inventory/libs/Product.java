package se.yrgo.inventory.libs;

import java.util.Objects;

public class Product implements Comparable<Product> {

    private int price;
    private String type;
    private int quantity;


    public Product(int price, String type, int quantity) {
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int compareTo(Product o) {
        return type.compareTo(o.type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;
        return price == product.price &&
                quantity == product.quantity &&
                type.equals(product.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, type, quantity);
    }
}

