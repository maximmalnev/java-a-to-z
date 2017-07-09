package ru.kovladimir.tictactoe.game;

/**
 * Checker with simple algorithm.
 */
public class BasicChecker implements Checker {

    /**
     * Amount to win.
     */
    private final int AMOUNT_TO_WIN;

    /**
     * Constructor.
     * @param amountToWin int.
     */
    public BasicChecker(int amountToWin) {
        this.AMOUNT_TO_WIN = amountToWin;
    }

    /**
     * Directions to go.
     */
    private Direction[] directions = new Direction[]{Direction.NORTH, Direction.NORTHEAST, Direction.EAST, Direction.SOUTHEAST,
            Direction.SOUTH, Direction.SOUTHWEST, Direction.WEST, Direction.NORTHWEST};

    /**
     * {@inheritDoc}
     * @param fields
     * @return
     */
    @Override
    public FieldType checkWinner(FieldType[][] fields) {
        FieldType winnerType = FieldType.NONE;
        boolean hasWinner = false;
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                int iteratorOfDirection = -1;
                while (!hasWinner && iteratorOfDirection < directions.length - 1) {
                    iteratorOfDirection++;
                    hasWinner = hasLineWinner(fields, i, j, directions[iteratorOfDirection]);
                }
                if (hasWinner) {
                    winnerType = fields[i][j];
                    break;
                }
            }
            if (hasWinner)
                break;
        }

        return winnerType;
    }

    /**
     * Check if one line in one direction has winner.
     * @param fields FieldType[][].
     * @param x int.
     * @param y int.
     * @param direction Direction.
     * @return boolean.
     */
    private boolean hasLineWinner(FieldType[][] fields, int x, int y, Direction direction) {
        boolean hasWinner = false;
        boolean stop = false;
        if (!(fields[x][y] == FieldType.NONE)) {
            FieldType startType = fields[x][y];
            for (int i = 0; i < AMOUNT_TO_WIN; i++) {
                if (validCoordinates(x, y, fields.length)) {
                    FieldType nextType = fields[x][y];
                    if (nextType != startType) {
                        stop = true;
                        break;
                    }
                    x = countX(direction, x);
                    y = countY(direction, y);
                } else stop = true;

                if (stop)
                    break;

                if (i == AMOUNT_TO_WIN - 1) {
                    hasWinner = true;
                }
            }
        }

        return hasWinner;
    }

    /**
     * Are x and y valid.
     * @param x int.
     * @param y int.
     * @param deskSize int.
     * @return boolean.
     */
    private boolean validCoordinates(int x, int y, int deskSize) {
        return x >= 0 && x < deskSize && y >= 0 && y < deskSize;
    }

    /**
     * Change x depending on the direction.
     * @param direction Direction.
     * @param x int.
     * @return int.
     */
    private int countX(Direction direction, int x) {
        int result = x;
        if (direction == Direction.NORTHEAST || direction == Direction.EAST || direction == Direction.SOUTHEAST) {
            result++;
        } else if (direction == Direction.SOUTHWEST || direction == Direction.WEST || direction == Direction.NORTHWEST) {
            result--;
        }
        return result;
    }

    /**
     * Change y depending on the direction.
     * @param direction Direction.
     * @param y int.
     * @return int.
     */
    private int countY(Direction direction, int y) {
        int result = y;
        if (direction == Direction.SOUTHEAST || direction == Direction.SOUTH || direction == Direction.SOUTHWEST) {
            result++;
        } else if (direction == Direction.NORTH || direction == Direction.NORTHEAST || direction == Direction.NORTHWEST) {
            result--;
        }
        return result;
    }

    /**
     * All possible directions.
     */
    private enum Direction {
        NORTH,
        NORTHEAST,
        EAST,
        SOUTHEAST,
        SOUTH,
        SOUTHWEST,
        WEST,
        NORTHWEST
    }
}
