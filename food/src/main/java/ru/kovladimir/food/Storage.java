package ru.kovladimir.food;

/**
 * Possible place to keep Food.
 */
public interface Storage {

    /**
     * Add product to the stock.
     * @param food Food.
     */
    boolean addFood(Food food);

    /**
     * Check if food is appropriate to this place.
     * @param food Food.
     * @return boolean.
     */
    boolean isAppropriate(Food food);

}
