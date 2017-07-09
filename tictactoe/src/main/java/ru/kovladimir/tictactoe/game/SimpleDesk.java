package ru.kovladimir.tictactoe.game;

/**
 * SimpleDesk with fields..
 */
public class SimpleDesk implements Desk{

    /**
     * Size of the desk.
     */
    private final int SIZE;

    /**
     * Array with fields.
     */
    private FieldType[][] fields;

    /**
     * Winner's checker.
     */
    private Checker checker;

    /**
     * Constructor.
     */
    public SimpleDesk(int size, Checker checker) {
        SIZE = size;
        fields = new FieldType[size][size];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fields[i][j] = FieldType.NONE;
            }
        }
        this.checker = checker;
    }

    /**
     * {@inheritDoc}
     * @param type FieldType.
     * @param x int.
     * @param y int.
     * @return
     */
    public boolean makeMove(FieldType type, int x, int y) {
        boolean wasAdded = false;
        if (isValidCoords(x - 1, y - 1) && fields[SIZE - y][x - 1] == FieldType.NONE) {
            fields[SIZE - y][x - 1] = type;
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * Check valid coords.
     *
     * @param x int.
     * @param y int.
     * @return boolean.
     */
    private boolean isValidCoords(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    public FieldType checkWinner() {
        return checker.checkWinner(fields);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    public boolean hasAvailableFields() {
        boolean has = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (fields[i][j] == FieldType.NONE) {
                    has = true;
                    break;
                }
            }
            if (has)
                break;
        }
        return has;
    }

    /**
     * Return string of all fields.
     *
     * @return String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < SIZE; i++) {
            builder.append(String.format(" %2d", SIZE - i));
            for (int j = 0; j < SIZE; j++) {
                builder.append(" ");
                builder.append(fields[i][j].toString());
                builder.append(" ");
            }
            builder.append(System.lineSeparator());
        }

        for (int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                builder.append("   ");
            } else {
                builder.append(" ");
                builder.append(i);
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
