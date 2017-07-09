package ru.kovladimir.tictactoe.player;

import ru.kovladimir.tictactoe.game.FieldType;

/**
 * Interface of Player.
 */
public abstract class Player {

    /**
     * Type Field of Player (Cross or Nought).
     */
    private FieldType fieldType;

    /**
     * Name.
     */
    private String name;

    /**
     * Constructor.
     * @param name String.
     * @param fieldType FieldType.
     */
    public Player(String name, FieldType fieldType) {
        this.fieldType = fieldType;
        this.name = name;
    }

    /**
     * Get player's name.
     * @return String.
     */
    public String getName() {
        return name;
    }

    /**
     * Set player's name.
     * @param name String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Player's FieldType.
     * @return FieldType.
     */
    public FieldType getFieldType() {
        return fieldType;
    }

    /**
     * Ask int from player's input.
     * @return int.
     */
    public abstract int askInt();

    /**
     * Ask String from player's input.
     * @return String.
     */
    public abstract String askString();

    /**
     * Show message to Player.
     * @param message String.
     */
    public abstract void showMessage(String message);




}
