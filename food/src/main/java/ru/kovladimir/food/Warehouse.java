package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Warehouse. Place to hold food.
 */
public class Warehouse extends SimpleStorage {

    /**
     * Default constructor.
     * @param today Calendar.
     */
    public Warehouse(Calendar today) {
        super(today);
    }

    /**
     * {@inheritDoc}
     * @param food Food.
     * @return
     */
    public boolean isAppropriate(Food food) {
        return timeCounter.getPassedTimeInPercentages(food, today) < 25;
    }
}
