package ru.kovladimir.food;

import java.util.Calendar;

/**
 * ReproductApple. Extended Version of Apple.
 */
public class AppleReproduct extends FoodReproduct {

    /**
     * Default constructor.
     *
     * @param name           String.
     * @param creationDate   Calender.
     * @param expirationDate Calender.
     * @param price          double.
     */
    public AppleReproduct(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
