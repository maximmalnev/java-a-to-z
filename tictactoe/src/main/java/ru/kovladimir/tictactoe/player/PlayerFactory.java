package ru.kovladimir.tictactoe.player;


import ru.kovladimir.tictactoe.game.FieldType;
import ru.kovladimir.tictactoe.io.ConsoleInput;
import ru.kovladimir.tictactoe.io.ConsoleOutput;

/**
 * Factory to create players.
 */
public class PlayerFactory {

    /**
     * Dsek size.
     */
    private int deskSize;

    /**
     * Constructor.
     * @param deskSize int.
     */
    public PlayerFactory(int deskSize) {
        this.deskSize = deskSize;
    }

    /**
     * Create human player.
     * @param type FieldType.
     * @return Player.
     */
    public Player createHumanPlayer(FieldType type) {
        Player player = new Human(type, new ConsoleOutput(), new ConsoleInput(), deskSize);
        player.showMessage("Please, enter your name:");
        String crossName = player.askString();
        player.setName(crossName);
        return player;
    }

    /**
     * Create simple computer player.
     * @param type FieldType.
     * @return Player.
     */
    public Player createSimpleComputerPlayer(FieldType type) {
        return new SimpleComputer(type, deskSize);
    }

    /**
     * Create programmed player.
     * @param type FieldType.
     * @param commands int[].
     * @return Player.
     */
    public Player createProgrammedPlayer(FieldType type, int[] commands) {
        return new ProgrammedPlayer(type, commands);
    }
}
