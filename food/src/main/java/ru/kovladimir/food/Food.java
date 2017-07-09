package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Abstract food.
 */
public abstract class Food {

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Date of the creation.
     */
    private Calendar creationDate;

    /**
     * Date of the expiration.
     */
    private Calendar expirationDate;

    /**
     * Price of the product.
     */
    private double price;

    /**
     * Discount.
     */
    private double discount = 0.0;

    /**
     * Default constructor.
     * @param name String.
     * @param creationDate Calender.
     * @param expirationDate Calender.
     * @param price double.
     */
    public Food(String name, Calendar creationDate, Calendar expirationDate, double price) {
        this.name = name;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}