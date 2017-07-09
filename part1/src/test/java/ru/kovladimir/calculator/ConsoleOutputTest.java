package ru.kovladimir.calculator;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputTest {

    @Test public void whenPrintMessageThenItOnConsole() {
        String data = "Test";
        String separator = System.lineSeparator();
        String result = data + separator;
        PrintStream defaultOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ConsoleOutput output = new ConsoleOutput();

        output.displayMessage(data);
        System.setOut(defaultOut);

        Assert.assertEquals(result, outContent.toString());
    }
}