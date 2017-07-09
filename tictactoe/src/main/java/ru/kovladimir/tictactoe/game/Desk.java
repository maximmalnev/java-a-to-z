package ru.kovladimir.tictactoe.game;

/**
 * Desk interface.
 */
public interface Desk {

    /**
     * Make move.
     * @param type FieldType.
     * @param x int.
     * @param y int.
     * @return boolean.
     */
    boolean makeMove(FieldType type, int x, int y);

    /**
     * Check winner in array.
     * @return FieldType.
     */
    FieldType checkWinner();

    /**
     * Has desk any available fields.
     *
     * @return boolean.
     */
    boolean hasAvailableFields();

}
