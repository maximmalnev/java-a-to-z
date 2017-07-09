package ru.kovladimir.food;

import java.util.Calendar;

/**
 * WarehouseFreezer. Place to hold FoodCold.
 */
public class WarehouseFreezer extends SimpleStorageCold{

    /**
     * Basec constructor.
     * @param today Calendar.
     */
    public WarehouseFreezer(Calendar today) {
        super(today);
    }

    /**
     * {@inheritDoc}
     * @param food FoodCold.
     * @return
     */
    public boolean isAppropriate(FoodCold food) {
        return timeCounter.getPassedTimeInPercentages(food, today) < 25 && food.isMustBeKeptInCold();
    }
}
