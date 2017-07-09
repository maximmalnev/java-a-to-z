package ru.kovladimir.bomberman.player;

import ru.kovladimir.bomberman.Bomb;
import ru.kovladimir.bomberman.Direction;
import ru.kovladimir.bomberman.Field;

/**
 * Created by Vladimir on 19.08.2016.
 */
public class Character extends AbstractPlayer {


    public Character(int startX, int startY, Field field) {
        super(startX, startY, field);
    }

    @Override
    public void makeMove(Direction direction) {
        if (isPossibleMove(direction)) {
            field.getBlock(x, y).setCharacter(false);
            changeCoordinates(direction);
            field.getBlock(x, y).setCharacter(true);
            boolean end = checkEndGame();
            // TODO: 21.08.2016 should stop the game if end == true
        }
    }

    public void setBomb() {
        new Bomb(x, y, field).setBomb();
    }

    @Override
    boolean checkEndGame() {
        return field.getBlock(x, y).hasMonsters();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //wait for user's' input

            // test version to get direction
            makeMove(Direction.getRandomDirection());

        }
    }


}
