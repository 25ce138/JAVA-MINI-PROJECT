package model;
import model.annotation.Id;

public class Customer {
    @Id
    private final String customerId;
    private final String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    @Override
    public String toString() {
        return customerId + " (" + name + ")";
    }
}
