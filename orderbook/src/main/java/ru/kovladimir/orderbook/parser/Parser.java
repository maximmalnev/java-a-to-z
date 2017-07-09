package ru.kovladimir.orderbook.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * XML Parse.
 */
public interface Parser {

    /**
     * Start parsers.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    void start() throws ParserConfigurationException, SAXException, IOException;

}
