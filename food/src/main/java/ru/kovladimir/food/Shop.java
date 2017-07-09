package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Shop. Place to sell food.
 */
public class Shop extends SimpleStorage {
    public Shop(Calendar today) {
        super(today);
    }

    public boolean isAppropriate(Food food) {
        double passedTimeInPercentages = timeCounter.getPassedTimeInPercentages(food, today);
        return passedTimeInPercentages >= 25 && passedTimeInPercentages < 100;
    }

    @Override
    public boolean addFood(Food food) {
        double passedTimeInPercentages = timeCounter.getPassedTimeInPercentages(food, today);
        if (passedTimeInPercentages > 75 && passedTimeInPercentages < 100)
            food.setDiscount(20.0);
        return super.addFood(food);
    }
}
