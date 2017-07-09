package ru.kovladimir.food;

import java.util.Calendar;

public abstract class SimpleStorageReproduct implements StorageReproduct{

    /**
     * Today date.
     */
    protected Calendar today;

    /**
     * Stock to keep food.
     */
    private FoodReproduct[] stock = new FoodReproduct[10];

    /**
     * Iterator of the array.
     */
    private int position = 0;

    /**
     * TimeCounter to calculate passed time in percentages.
     */
    protected TimeCounter timeCounter = new TimeCounter();

    /**
     * Getter of the array.
     * @return Food[].
     */
    public FoodReproduct[] getStock() {
        return stock;
    }

    /**
     * Constructor.
     * @param today Calendar.
     */
    public SimpleStorageReproduct(Calendar today) {
        this.today = today;
    }

    /**
     * {@inheritDoc}
     * @param food
     */
    public boolean addFood(FoodReproduct food) {
        boolean wasAdded = false;
        if (isAppropriate(food)) {
            stock[position++] = food;
            wasAdded = true;
        }
        return wasAdded;
    }
}
