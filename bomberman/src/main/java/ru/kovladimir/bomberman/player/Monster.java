package ru.kovladimir.bomberman.player;

import ru.kovladimir.bomberman.Direction;
import ru.kovladimir.bomberman.Field;

/**
 * Created by Vladimir on 19.08.2016.
 */
public class Monster extends AbstractPlayer {


    public Monster(int startX, int startY, Field field) {
        super(startX, startY, field);
    }

    @Override
    public void makeMove(Direction direction) {
        if (isPossibleMove(direction)) {
            field.getBlock(x, y).deleteMonster(this);
            changeCoordinates(direction);
            field.getBlock(x, y).addMonster(this);
            checkEndGame();
            boolean end = checkEndGame();
            // TODO: 21.08.2016 should stop the game if end == true
        }
    }

    @Override
    boolean checkEndGame() {
        return field.getBlock(x, y).hasCharacter();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
            makeMove(Direction.getRandomDirection());
        }
    }

}
