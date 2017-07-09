package ru.kovladimir.bomberman.player;

import ru.kovladimir.bomberman.BlockType;
import ru.kovladimir.bomberman.Direction;
import ru.kovladimir.bomberman.Field;

/**
 * Created by Vladimir on 19.08.2016.
 */
public abstract class AbstractPlayer extends Thread{

    protected final Field field;

    protected int x;

    protected int y;

    public AbstractPlayer(int startX, int startY, Field field) {
        this.field = field;
        this.x = startX;
        this.y = startY;
    }

    abstract void makeMove(Direction direction);

    abstract boolean checkEndGame();

    public boolean isPossibleMove(Direction direction) {
        boolean isPossible = false;
        if (direction == Direction.UP) {
            isPossible = (y + 1) < field.getHEIGHT() && field.getBlock(x, y + 1).getType() == BlockType.EMPTY;
        } else if (direction == Direction.RIGHT) {
            isPossible = (x + 1) < field.getWIDTH() && field.getBlock(x + 1, y).getType() == BlockType.EMPTY;
        } else if (direction == Direction.DOWN) {
            isPossible = (y - 1) >= 0 && field.getBlock(x, y - 1).getType() == BlockType.EMPTY;
        } else if (direction == Direction.LEFT) {
            isPossible = (x - 1) >= 0 && field.getBlock(x - 1, y).getType() == BlockType.EMPTY;
        }
        return isPossible;
    }

    public void changeCoordinates(Direction direction) {
        if (direction == Direction.UP) {
            y++;
        } else if (direction == Direction.RIGHT) {
            x++;
        } else if (direction == Direction.DOWN) {
            y--;
        } else if (direction == Direction.LEFT) {
            x--;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
