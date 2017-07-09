package ru.kovladimir.orderbook;

import org.junit.Test;
import ru.kovladimir.orderbook.params.ParamsImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MarketTest {

    private void createTestXmlFile() {
        try (PrintStream printer = new PrintStream(new FileOutputStream("orders.xml"))) {
            printer.println("<orders>");
            printer.println("<AddOrder book=\"book-1\" operation=\"SELL\" price=\"100.50\" volume=\"81\" orderId=\"1\" />");
            printer.println("<AddOrder book=\"book-3\" operation=\"BUY\" price=\" 99.50\" volume=\"86\" orderId=\"2\" />");
            printer.println("<AddOrder book=\"book-1\" operation=\"BUY\" price=\" 99.70\" volume=\"16\" orderId=\"3\" />");
            printer.println("<DeleteOrder book=\"book-3\" orderId=\"2\" />");
            printer.println("</orders>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestXmlFile()  {
        try {
            Files.delete(Paths.get("orders.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenParseXMLThenPrintResult() {
        createTestXmlFile();
        Market market = new Market(new ParamsImpl(new String[]{"-path", "orders.xml"}));
        String separator = System.lineSeparator();
        String table = separator +
                "     Order book: book-3" + separator +
                "_____________________________" + separator +
                "Volume@Price  |  Volume@Price" + separator +
                "_____________________________" + separator +
                separator +
                separator +
                "     Order book: book-1" + separator +
                "_____________________________" + separator +
                "Volume@Price  |  Volume@Price" + separator +
                "   16@ 99,70  |   81@100,50  " + separator +
                "_____________________________" + separator +
                separator;
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        market.start();
        System.setOut(oldOut);
        deleteTestXmlFile();

        assertThat(outContent.toString(), is(table));
    }

}