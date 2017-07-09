package ru.kovladimir.tictactoe.player;

import ru.kovladimir.tictactoe.game.FieldType;

import java.util.Random;

/**
 * Simple computer with random moves.
 */
public class SimpleComputer extends Player {

    /**
     * Random.
     */
    private Random random = new Random();

    /**
     * Max value (size of the desk.
     */
    private int deskSize;


    /**
     * Constructor with deskSize.
     * @param fieldType FieldType.
     * @param deskSize int.
     */
    public SimpleComputer(FieldType fieldType, int deskSize) {
        super("Simple Computer", fieldType);
        this.deskSize = deskSize;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int askInt() {
        return random.nextInt(deskSize) + 1;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String askString() {
        return "";
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public void showMessage(String message) {

    }
}
