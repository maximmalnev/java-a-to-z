package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Abstract implementation of Storage.
 */
public abstract class SimpleStorage implements Storage {

    /**
     * Today date.
     */
    protected Calendar today;

    /**
     * Stock to keep food.
     */
    private Food[] stock = new Food[10];

    /**
     * Iterator of the array.
     */
    private int position = 0;

    protected TimeCounter timeCounter = new TimeCounter();

    /**
     * Getter of the array.
     * @return Food[].
     */
    public Food[] getStock() {
        return stock;
    }


    public SimpleStorage(Calendar today) {
        this.today = today;
    }

    /**
     * {@inheritDoc}
     * @param food
     */
    public boolean addFood(Food food) {
        boolean wasAdded = false;
        if (isAppropriate(food)) {
            stock[position++] = food;
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     *{@inheritDoc}
     * @param name
     * @return
     */
}
