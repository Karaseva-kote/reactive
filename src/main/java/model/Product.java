package model;

import org.bson.Document;

public class Product {
    private final String name;
    private final Price price;

    public Product(Document doc) {
        this(doc.getString("name"), new Price(doc.getDouble("price")));
    }

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public String show(Currency currency) {
        return "name: " + name + ", cost: " + String.format("%.2f", price.toCurrency(currency)) + " " + currency;
    }
}
