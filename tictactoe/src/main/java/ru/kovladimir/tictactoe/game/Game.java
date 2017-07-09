package ru.kovladimir.tictactoe.game;

import ru.kovladimir.tictactoe.player.Player;

/**
 * Main class.
 */
public class Game {

    /**
     * SimpleDesk with fields.
     */
    private Desk desk;

    /**
     * Cross player.
     */
    private Player crossPlayer;

    /**
     * Nought player.
     */
    private Player noughtPlayer;

    /**
     * Current player.
     */
    private Player currentPlayer;

    /**
     * Winner.
     */
    private Player winner;

    /**
     * Loser.
     */
    private Player loser;

    /**
     * Has winner or not.
     */
    private boolean hasWinner = false;

    public Game(Desk desk, Player crossPlayer, Player noughtPlayer) {
        this.desk = desk;
        this.crossPlayer = crossPlayer;
        this.noughtPlayer = noughtPlayer;
    }

    /**
     * Main loop.
     */
    public void start() {
        currentPlayer = noughtPlayer;
        while (!hasWinner && desk.hasAvailableFields()) {
            changeCurrentPlayer();
            printDeskToCurrentPlayer();
            makeMove();
            checkWinner();
        }
        printResultToBothPlayers();
    }


    /**
     * Change current player.
     */
    public void changeCurrentPlayer() {
        if (currentPlayer == noughtPlayer) {
            currentPlayer = crossPlayer;
        } else {
            currentPlayer = noughtPlayer;
        }
    }

    /**
     * Print simpleDesk to current player.
     */
    public void printDeskToCurrentPlayer() {
        currentPlayer.showMessage(desk.toString());
    }

    /**
     * Current player should make move.
     */
    public void makeMove() {
        currentPlayer.showMessage(String.format("%s, enter x coordinate", currentPlayer.getName()));
        int x = currentPlayer.askInt();
        currentPlayer.showMessage(String.format("%s, enter y coordinate", currentPlayer.getName()));
        int y = currentPlayer.askInt();
        boolean moveWasMade = desk.makeMove(currentPlayer.getFieldType(), x, y);
        if (!moveWasMade) {
            currentPlayer.showMessage("This field is already set. Please, try again.");
            makeMove();
        }
    }

    /**
     * Check winner.
     */
    public void checkWinner() {
        FieldType winnerType = desk.checkWinner();
        if (winnerType == FieldType.CROSS) {
            winner = crossPlayer;
            loser = noughtPlayer;
        } else if (winnerType == FieldType.NOUGHT) {
            winner = noughtPlayer;
            loser = crossPlayer;
        }
        if (winner != null)
            hasWinner = true;
    }

    /**
     * Print result to winner and loser.
     */
    private void printResultToBothPlayers() {
        if (!hasWinner) {
            crossPlayer.showMessage(desk.toString());
            crossPlayer.showMessage("Draw! No one won.");
            noughtPlayer.showMessage(desk.toString());
            noughtPlayer.showMessage("Draw! No one won.");
        } else {
            winner.showMessage(desk.toString());
            winner.showMessage(String.format("%s, you won! Congratulations!", winner.getName()));
            loser.showMessage(desk.toString());
            loser.showMessage(String.format("%s, you lose!", loser.getName()));
        }
    }
}
