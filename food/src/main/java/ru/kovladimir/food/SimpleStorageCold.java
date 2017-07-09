package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Abstract implementation of StorageCold.
 */
public abstract class SimpleStorageCold implements StorageCold {
    /**
     * Today date.
     */
    protected Calendar today;

    /**
     * Stock to keep food.
     */
    private FoodCold[] stock = new FoodCold[10];

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
    public FoodCold[] getStock() {
        return stock;
    }

    /**
     * Constructor.
     * @param today Calendar.
     */
    public SimpleStorageCold(Calendar today) {
        this.today = today;
    }

    /**
     * {@inheritDoc}
     * @param food
     */
    public boolean addFood(FoodCold food) {
        boolean wasAdded = false;
        if (isAppropriate(food)) {
            stock[position++] = food;
            wasAdded = true;
        }
        return wasAdded;
    }
}
