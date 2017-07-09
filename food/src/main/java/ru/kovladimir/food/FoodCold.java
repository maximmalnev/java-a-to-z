package ru.kovladimir.food;

import java.util.Calendar;

public class FoodCold extends FoodReproduct {

    private boolean mustBeKeptInCold = false;

    public boolean isMustBeKeptInCold() {
        return mustBeKeptInCold;
    }

    public void setMustBeKeptInCold(boolean mustBeKeptInCold) {
        this.mustBeKeptInCold = mustBeKeptInCold;
    }

    /**
     * Default constructor.
     *
     * @param name           String.
     * @param creationDate   Calender.
     * @param expirationDate Calender.
     * @param price          double.
     */
    public FoodCold(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
