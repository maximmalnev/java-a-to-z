package ru.kovladimir.tictactoe.player;

import ru.kovladimir.tictactoe.game.FieldType;
import ru.kovladimir.tictactoe.io.Input;
import ru.kovladimir.tictactoe.io.Output;

/**
 * Human.
 */
public class Human extends Player {

    /**
     * Output to interact with player.
     */
    private Output output;

    /**
     * Input to interact with player.
     */
    private Input input;

    /**
     * Max int (size of the desk).
     */
    private int deskSize;

    /**
     * Constructor with deskSize.
     * @param fieldType FieldType.
     * @param output Output.
     * @param input Input.
     * @param deskSize int.
     */
    public Human(FieldType fieldType, Output output, Input input, int deskSize) {
        super("Default name", fieldType);
        this.output = output;
        this.input = input;
        this.deskSize = deskSize;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int askInt() {
        int value;
        String lineFromUser = input.askString();
        try {
            value = Integer.parseInt(lineFromUser);
        } catch (NumberFormatException e) {
            output.showMessage("Not valid value. Please, try again.");
            value = askInt();
        }
        if (value > deskSize) {
            output.showMessage("This number is too big. Please, try again.");
            value = askInt();
        } else if (value <= 0) {
            output.showMessage("This number is too small. Please, try again.");
            value = askInt();
        }

        return value;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String askString() {
        return input.askString();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public void showMessage(String message) {
        output.showMessage(message);
    }


}
