package ru.kovladimir.orderbook.io;

import ru.kovladimir.orderbook.Order;
import ru.kovladimir.orderbook.OrderBook;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Console Output.
 */
public class ConsoleOutput implements Output {

    /**
     * {@inheritDoc}
     * @param line String.
     */
    @Override
    public void println(String line) {
        System.out.println(line);
    }

    /**
     * {@inheritDoc}
     * @param line String.
     */
    @Override
    public void print(String line) {
        System.out.print(line);
    }

    /**
     * {@inheritDoc}
     * @param format String.
     * @param args Object...
     */
    @Override
    public void printf(String format, Object ... args) {
        System.out.printf(format, args);
    }

    /**
     * {@inheritDoc}
     * @param book OrderBook.
     */
    @Override
    public void printBook(OrderBook book) {
        printName(book.getName());
        printValues(book);
        printVault();
    }

    /**
     * Print book's name.
     * @param name String.
     */
    private void printName(String name) {
        println("");
        printf("     Order book: %s", name);
        println("");
        println("_____________________________");
        println("Volume@Price  |  Volume@Price");
    }

    /**
     * Print all values in book.
     */
    @SuppressWarnings("unchecked")
    private void printValues(OrderBook book) {
        TreeMap<Double, List<Order>> buyByPrice = book.getMapBuyByPrice();
        TreeMap<Double, List<Order>> sellByPrice = book.getMapSellByPrice();

        Object[] buyEntries = buyByPrice.entrySet().toArray();
        Object[] sellEntries = sellByPrice.entrySet().toArray();

        int buySize = buyEntries.length;
        int sellSize = sellEntries.length;

        int max = buySize > sellSize ? buySize : sellSize;
        int min = max == buySize ? sellSize : buySize;

        for (int i = 0; i < min; i++) {
            printEntry((Map.Entry<Double, List<Order>>) buyEntries[i]);
            print("|");
            printEntry((Map.Entry<Double, List<Order>>) sellEntries[i]);
            println("");
        }

        for (int i = min; i < max; i++) {
            if (buySize == min) {
                print("   ---------  |");
                printEntry((Map.Entry<Double, List<Order>>) sellEntries[i]);
                println("");
            } else if (sellSize == min) {
                printEntry((Map.Entry<Double, List<Order>>) buyEntries[i]);
                print("| ---------   ");
                println("");
            }
        }
    }

    /**
     * Print one pair 'volume@price'
     * @param entry Entry.
     */
    private void printEntry(Map.Entry<Double, List<Order>> entry) {
        int volume = 0;
        for (Order order : entry.getValue()) {
            volume += order.getQuantity();
        }
        printf(" %4d@%6.2f  ", volume, entry.getKey());
    }

    /**
     * Print vault.
     */
    private void printVault() {
        println("_____________________________");
        println("");
    }

}
