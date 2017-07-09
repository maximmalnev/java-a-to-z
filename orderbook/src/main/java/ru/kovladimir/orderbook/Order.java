package ru.kovladimir.orderbook;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Order.
 */
public class Order {

    /**
     * Name of product.
     */
    private String productName;

    /**
     * Operation.
     */
    private Operation operation;

    /**
     * Price.
     */
    private double price;

    /**
     * Quantity.
     */
    private long quantity;

    /**
     * Order's ID.
     */
    private long id;

    /**
     * Constructor.
     * @param productName String.
     * @param operation Operation.
     * @param price double.
     * @param quantity long.
     * @param id long.
     */
    public Order(String productName, Operation operation, double price, long quantity, long id) {
        this.productName = productName;
        this.operation = operation;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public Operation getOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
