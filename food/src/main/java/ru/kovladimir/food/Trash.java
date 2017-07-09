package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Trash. Place to utilize food.
 */
public class Trash extends SimpleStorage {
    public Trash(Calendar today) {
        super(today);
    }

    public boolean isAppropriate(Food food) {
        return timeCounter.getPassedTimeInPercentages(food, today) >= 100;
    }
}
