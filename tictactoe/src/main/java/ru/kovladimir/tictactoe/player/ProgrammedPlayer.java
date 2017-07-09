package ru.kovladimir.tictactoe.player;

import ru.kovladimir.tictactoe.game.FieldType;

/**
 *
 */
public class ProgrammedPlayer extends Player {

    /**
     * Array of commands.
     */
    private int[] commands;

    /**
     * Iterator.
     */
    private int iteratorOfIntCommands = 0;

    /**
     * Constructor.
     * @param fieldType FieldType
     * @param commands int[].
     */
    public ProgrammedPlayer(FieldType fieldType, int[] commands) {
        super("Programmed Player", fieldType);
        this.commands = commands;
    }

    @Override
    public int askInt() {
        return commands[iteratorOfIntCommands++];
    }

    @Override
    public String askString() {
        return "";
    }

    @Override
    public void showMessage(String message) {

    }
}
