package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Apple.
 */
public class Apple extends Food {

    /**
     * {@inheritDoc}
     * @param name
     * @param creationDate
     * @param expirationDate
     * @param price
     */
    public Apple(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
