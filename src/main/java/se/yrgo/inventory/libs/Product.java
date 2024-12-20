package se.yrgo.inventory.libs;

import java.util.Objects;

/**
 * Representerar en produkt i ett lager med attribut som pris, typ och kvantitet.
 * Klassen implementerar Comparable för att möjliggöra jämförelse av produkter baserat på produktens typ.
 */
public class Product implements Comparable<Product> {

    private int price;
    private String type;
    private int quantity;

    /**
     * Skapar en ny produkt med angivet pris, typ och kvantitet.
     *
     * @param price Produktens pris.
     * @param type Produktens typ, t.ex. "Bok", "Kaffe".
     * @param quantity Produktens kvantitet.
     */
    public Product(int price, String type, int quantity) {
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }

    /**
     * Hämtar kvantiteten av produkten.
     *
     * @return Produktens kvantitet.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sätter kvantiteten av produkten.
     *
     * @param quantity Den nya kvantiteten.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Hämtar priset på produkten.
     *
     * @return Produktens pris.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sätter priset på produkten.
     *
     * @param price Det nya priset.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Hämtar typen av produkten.
     *
     * @return Produktens typ, t.ex. "Bok", "Kaffe".
     */
    public String getType() {
        return type;
    }

    /**
     * Sätter typen av produkten.
     *
     * @param type Den nya typen.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Jämför denna produkt med en annan produkt baserat på produkttyp.
     *
     * @param o Den andra produkten att jämföra med.
     * @return Ett negativt tal om denna produkt är mindre än den andra, 0 om de är lika, och ett positivt tal om denna produkt är större.
     */
    public int compareTo(Product o) {
        return type.compareTo(o.type);
    }

    /**
     * Jämför denna produkt med ett annat objekt för att avgöra om de är lika.
     * Två produkter anses vara lika om deras pris, typ och kvantitet är lika.
     *
     * @param obj Objektet att jämföra med.
     * @return true om objekten är lika, false annars.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;
        return price == product.price &&
                quantity == product.quantity &&
                type.equals(product.type);
    }

    /**
     * Beräknar hashkoden för denna produkt baserat på dess pris, typ och kvantitet.
     *
     * @return Hashkoden för produkten.
     */
    @Override
    public int hashCode() {
        return Objects.hash(price, type, quantity);
    }
}


// la till en kommentar här bara för att den ville inte commita filen annars..