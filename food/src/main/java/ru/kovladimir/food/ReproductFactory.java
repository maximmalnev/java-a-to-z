package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Factory to reproduct food.
 */
public class ReproductFactory extends SimpleStorageReproduct {

    /**
     * Default constructor.
     * @param today Calendar.
     */
    public ReproductFactory(Calendar today) {
        super(today);
    }

    /**
     * {@inheritDoc}
     * @param food
     * @return
     */
    public boolean isAppropriate(FoodReproduct food) {
        double passed = timeCounter.getPassedTimeInPercentages(food, today);
        return passed >= 100 && food.isCanBeReproducted();
    }
}
