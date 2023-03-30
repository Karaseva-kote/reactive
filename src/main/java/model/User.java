package model;

import org.bson.Document;

public class User {
    private final String name;
    private final Currency currency;

    public User(Document doc) {
        this(doc.getString("name"), doc.getString("currency"));
    }

    private User(String name, String currency) {
        this.name = name;
        switch (currency) {
            case "USD" -> this.currency = Currency.USD;
            case "EUR" -> this.currency = Currency.EUR;
            default -> this.currency = Currency.RUB;
        }
    }

    public Currency getCurrency() {
        return currency;
    }
}
