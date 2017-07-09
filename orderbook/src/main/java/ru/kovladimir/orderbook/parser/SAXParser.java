package ru.kovladimir.orderbook.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.kovladimir.orderbook.Market;
import ru.kovladimir.orderbook.Operation;
import ru.kovladimir.orderbook.Order;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * SAX Parser.
 */
public class SAXParser extends DefaultHandler implements Parser {

    /**
     * Market.
     */
    private Market market;

    /**
     * XML File.
     */
    private File file;

    /**
     * Main rarser with logic.
     */
    private javax.xml.parsers.SAXParser parser;

    /**
     * Constructor.
     * @param market Market.
     * @param file File.
     */
    public SAXParser(Market market, File file) {
        this.market = market;
        this.file = file;
    }

    /**
     * {@inheritDoc}
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    @Override
    public void start() throws ParserConfigurationException, SAXException, IOException {
        parser = SAXParserFactory.newInstance().newSAXParser();
        parser.parse(file, this);
    }

    /**
     * {@inheritDoc}
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        if (qName.startsWith("A")) {
            String productName = attributes.getValue("book");
            Operation operation = attributes.getValue("operation").startsWith("B") ? Operation.BUY : Operation.SELL;
            double price = Double.parseDouble(attributes.getValue("price"));
            long quantity = Long.parseLong(attributes.getValue("volume"));
            long id = Long.parseLong(attributes.getValue("orderId"));
            market.add(new Order(productName, operation, price, quantity, id));
        } else if (qName.startsWith("D")) {
            String productName = attributes.getValue("book");
            long id = Long.parseLong(attributes.getValue("orderId"));
            market.delete(new Order(productName, null, 0.0, 0, id));
        }

    }
}
