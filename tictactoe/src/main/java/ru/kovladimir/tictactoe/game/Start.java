package ru.kovladimir.tictactoe.game;

/**
 * Start game.
 */
public class Start {

    public static void main(String[] args) {
        int size = 3;
        int amountToWin = 3;
        Game game = new GameFactory().createSimpleComputerAgainstHuman(size, new BasicChecker(amountToWin));
        game.start();
    }

}
