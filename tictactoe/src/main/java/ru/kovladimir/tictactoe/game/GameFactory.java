package ru.kovladimir.tictactoe.game;

import ru.kovladimir.tictactoe.player.Player;
import ru.kovladimir.tictactoe.player.PlayerFactory;

/**
 * Game factory (choose players).
 */
public class GameFactory {

    /**
     * Create game. First player - human. Second player - simple computer.
     * @param deskSize int.
     * @param checker Checker.
     * @return Game.
     */
    public Game createHumanAgainstSimpleComputer(int deskSize, Checker checker) {
        PlayerFactory factory = new PlayerFactory(deskSize);
        Player crossPlayer = factory.createHumanPlayer(FieldType.CROSS);
        Player noughtPlayer = factory.createSimpleComputerPlayer(FieldType.NOUGHT);
        Desk desk = new SimpleDesk(deskSize, checker);
        return new Game(desk, crossPlayer, noughtPlayer);
    }

    /**
     * Create game. First player - simple computer. Second player - human.
     * @param deskSize int.
     * @param checker Checker.
     * @return Game.
     */
    public Game createSimpleComputerAgainstHuman(int deskSize, Checker checker) {
        PlayerFactory factory = new PlayerFactory(deskSize);
        Player crossPlayer = factory.createSimpleComputerPlayer(FieldType.CROSS);
        Player noughtPlayer = factory.createHumanPlayer(FieldType.NOUGHT);
        Desk desk = new SimpleDesk(deskSize, checker);
        return new Game(desk, crossPlayer, noughtPlayer);
    }

    /**
     * Create game. First player - human. Second player - human.
     * @param deskSize int.
     * @param checker Checker.
     * @return Game.
     */
    public Game createHumanAgainstHuman(int deskSize, Checker checker) {
        PlayerFactory factory = new PlayerFactory(deskSize);
        Player crossPlayer = factory.createHumanPlayer(FieldType.CROSS);
        Player noughtPlayer = factory.createHumanPlayer(FieldType.NOUGHT);
        Desk desk = new SimpleDesk(deskSize, checker);
        return new Game(desk, crossPlayer, noughtPlayer);
    }
}
