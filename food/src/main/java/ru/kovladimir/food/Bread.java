package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Bread.
 */
public class Bread extends Food {

    /**
     * {@inheritDoc}
     * @param name
     * @param creationDate
     * @param expirationDate
     * @param price
     */
    public Bread(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
