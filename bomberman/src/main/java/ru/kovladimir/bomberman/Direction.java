package ru.kovladimir.bomberman;

import java.util.Random;

/**
 * Created by Vladimir on 19.08.2016.
 */
public enum  Direction {

    UP,
    RIGHT,
    DOWN,
    LEFT;

    private static Random random = new Random();

    public static Direction getRandomDirection() {
        int position = random.nextInt(values().length);
        return values()[position];
    }

}
