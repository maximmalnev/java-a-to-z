package ru.kovladimir.calculator;

/**
 * Console output.
 */
public class ConsoleOutput implements Output{

    /**
     * {@inheritDoc}
     * @param message String.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
