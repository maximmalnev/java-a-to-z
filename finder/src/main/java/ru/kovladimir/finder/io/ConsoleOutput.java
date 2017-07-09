package ru.kovladimir.finder.io;

/**
 * Console Output.
 */
public class ConsoleOutput implements Output{

    /**
     * {@inheritDoc}
     * @param message String.
     */
    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
