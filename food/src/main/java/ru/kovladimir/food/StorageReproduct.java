package ru.kovladimir.food;

/**
 * Possible place to keep FoodReproduct.
 */
public interface StorageReproduct {

    /**
     * Add product to the stock.
     * @param food FoodReproduct.
     */
    boolean addFood(FoodReproduct food);

    /**
     * Check if food is appropriate to this place.
     * @param food FoodReproduct.
     * @return boolean.
     */
    boolean isAppropriate(FoodReproduct food);

}
