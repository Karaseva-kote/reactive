package model;

public class Price {
    private final double price;
    public Price(double price) {
        this.price = price;
    }

    public double toCurrency(Currency currency) {
        return switch (currency) {
            case RUB -> price;
            case USD -> price / 77;
            case EUR -> price / 83;
        };
    }
}
