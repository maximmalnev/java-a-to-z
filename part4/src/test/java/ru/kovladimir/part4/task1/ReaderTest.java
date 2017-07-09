package ru.kovladimir.part4.task1;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ReaderTest {

    @Test
    public void whenInputEvenThenPrintEvenMessage() throws IOException {
        String inputString = "2";
        String message = "This is even number." + System.lineSeparator();
        ByteArrayInputStream inContent = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        EvenOddChecker reader = new EvenOddChecker(inContent, new PrintStream(outContent));

        reader.check();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenInputOddThenPrintOddMessage() throws IOException {
        String inputString = "3";
        String message = "This is odd number." + System.lineSeparator();
        ByteArrayInputStream inContent = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        EvenOddChecker reader = new EvenOddChecker(inContent, new PrintStream(outContent));

        reader.check();
        inContent.close();
        outContent.close();


        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenInputNotIntThenPrintError() throws IOException {
        String inputString = "Not integer";
        String message = "This is not integer." + System.lineSeparator();
        ByteArrayInputStream inContent = new ByteArrayInputStream(inputString.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        EvenOddChecker reader = new EvenOddChecker(inContent, new PrintStream(outContent));

        reader.check();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

}