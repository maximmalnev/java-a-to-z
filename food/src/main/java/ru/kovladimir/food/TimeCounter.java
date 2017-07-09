package ru.kovladimir.food;

import java.util.Calendar;

/**
 * Class to count time in percentages.
 */
public class TimeCounter {


    /**
     * Count time in percentages.
     * @param food Food.
     * @param today Calendar.
     * @return result.
     */
    public double getPassedTimeInPercentages(Food food, Calendar today) {
        double creation = food.getCreationDate().getTimeInMillis();
        double expiration = food.getExpirationDate().getTimeInMillis();
        double duration = expiration - creation;
        double passed = today.getTimeInMillis() - creation;

        return passed * 100 / duration;
    }

}
