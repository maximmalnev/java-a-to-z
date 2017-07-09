package ru.kovladimir.orderbook.io;

import ru.kovladimir.orderbook.OrderBook;

/**
 * Output.
 */
public interface Output {

    /**
     * Print and go next line.
     * @param line String.
     */
    void println(String line);

    /**
     * Print.
     * @param line String.
     */
    void print(String line);

    /**
     * Print with format.
     * @param format String.
     * @param args Object...
     */
    void printf(String format, Object... args);

    /**
     * Print book.
     * @param book OrderBook.
     */
    void printBook(OrderBook book);
}
