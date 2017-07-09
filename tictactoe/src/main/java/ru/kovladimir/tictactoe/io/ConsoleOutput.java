package ru.kovladimir.tictactoe.io;

/**
 * Console output.
 */
public class ConsoleOutput implements Output {

    /**
     * {@inheritDoc}
     * @param message String.
     */
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
