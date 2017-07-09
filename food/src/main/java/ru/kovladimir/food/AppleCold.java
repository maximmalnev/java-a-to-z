package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Apple that can be kept in cold.
 */
public class AppleCold extends FoodCold {
    /**
     * Default constructor.
     *
     * @param name           String.
     * @param creationDate   Calender.
     * @param expirationDate Calender.
     * @param price          double.
     */
    public AppleCold(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
