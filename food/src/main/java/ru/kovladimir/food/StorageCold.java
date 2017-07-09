package ru.kovladimir.food;

/**
 * Possible place to keep FoodCold.
 */
public interface StorageCold {

    /**
     * Add product to the stock.
     * @param food FoodCold.
     */
    boolean addFood(FoodCold food);

    /**
     * Check if food is appropriate to this place.
     * @param food FoodCold.
     * @return boolean.
     */
    boolean isAppropriate(FoodCold food);
}
