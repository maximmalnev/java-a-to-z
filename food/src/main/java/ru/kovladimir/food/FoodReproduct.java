package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Food that can be reproducted.
 */
public abstract class FoodReproduct extends Food {

    /**
     * canBeReproducted.
     */
    private boolean canBeReproducted;

    public boolean isCanBeReproducted() {
        return canBeReproducted;
    }

    public void setCanBeReproducted(boolean canBeReproducted) {
        this.canBeReproducted = canBeReproducted;
    }

    /**
     * Default constructor.
     *
     * @param name           String.
     * @param creationDate   Calender.
     * @param expirationDate Calender.
     * @param price          double.
     */
    public FoodReproduct(String name, Calendar creationDate, Calendar expirationDate, double price) {
        super(name, creationDate, expirationDate, price);
    }
}
