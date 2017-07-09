package ru.kovladimir.tictactoe.game;

/**
 * Checker that start winner.
 */
public interface Checker {

    /**
     * Check if array has winner.
     */
    FieldType checkWinner(FieldType[][] fields);

}
