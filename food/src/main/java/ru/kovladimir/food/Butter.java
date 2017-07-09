package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Butter.
 */
public class Butter extends Food {

    /**
     * {@inheritDoc}
     * @param name
     * @param creationDate
     * @param expirationDate
     * @param price
     */
    public Butter(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
