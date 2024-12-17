package se.yrgo.inventory.libs;

public class Product {

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


}

