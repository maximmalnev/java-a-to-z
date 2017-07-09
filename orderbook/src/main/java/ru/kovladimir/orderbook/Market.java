package ru.kovladimir.orderbook;

import org.xml.sax.SAXException;
import ru.kovladimir.orderbook.io.ConsoleOutput;
import ru.kovladimir.orderbook.io.Output;
import ru.kovladimir.orderbook.params.NotValidArgsException;
import ru.kovladimir.orderbook.params.Params;
import ru.kovladimir.orderbook.params.ParamsImpl;
import ru.kovladimir.orderbook.parser.SAXParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

/**
 * Market.
 */
public class Market {

    /**
     * Printer to print result.
     */
    private Output output = new ConsoleOutput();

    /**
     * All books.
     */
    private Map<String, OrderBook> books = new HashMap<>();

    /**
     * Params.
     */
    private Params params;

    /**
     * Constructor.
     *
     * @param params Params.
     */
    public Market(Params params) {
        this.params = params;
    }



    /**
     * Add order.
     *
     * @param order Order.
     */
    public void add(Order order) {
        String productName = order.getProductName();
        OrderBook book = books.get(productName);
        if (book == null) {
            book = new OrderBook(productName);
            books.put(productName, book);
        }
        book.add(order);
    }

    /**
     * Delete order.
     *
     * @param order Order.
     */
    public void delete(Order order) {
        String productName = order.getProductName();
        OrderBook book = books.get(productName);
        book.delete(order);
    }

    /**
     * Start.
     */
    public void start() {
        try {
            new SAXParser(this, params.getFile()).start();
            for (OrderBook book : books.values()) {
                output.printBook(book);
            }
        } catch (SAXException | ParserConfigurationException | NotValidArgsException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //Date start = new Date();
        Market market = new Market(new ParamsImpl(args));
        market.start();
        //Date end = new Date();
        //System.out.println(end.getTime() - start.getTime());
    }
}
