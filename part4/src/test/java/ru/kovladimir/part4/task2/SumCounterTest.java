package ru.kovladimir.part4.task2;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SumCounterTest {

    @Test
    public void whenBothIntThenPrintSum() throws IOException {
        String separator = System.lineSeparator();
        String first = "1";
        String second = "2";
        String message = "3" + separator;
        ByteArrayInputStream inContent = new ByteArrayInputStream((first + separator + second).getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        SumCounter counter = new SumCounter(inContent, new PrintStream(outContent));


        counter.start();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenFirstNotIntThenPrintSum() throws IOException {
        String separator = System.lineSeparator();
        String first = "fail";
        String second = "2";
        String message = "This is not integer." + separator;
        ByteArrayInputStream inContent = new ByteArrayInputStream((first + separator + second).getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        SumCounter counter = new SumCounter(inContent, new PrintStream(outContent));


        counter.start();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenSecondNotIntThenPrintSum() throws IOException {
        String separator = System.lineSeparator();
        String first = "1";
        String second = "fail";
        String message = "This is not integer." + separator;
        ByteArrayInputStream inContent = new ByteArrayInputStream((first + separator + second).getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        SumCounter counter = new SumCounter(inContent, new PrintStream(outContent));

        counter.start();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenBothNotIntThenPrintSum() throws IOException {
        String separator = System.lineSeparator();
        String first = "fail1";
        String second = "fail2";
        String message = "This is not integer." + separator;
        ByteArrayInputStream inContent = new ByteArrayInputStream((first + separator + second).getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        SumCounter counter = new SumCounter(inContent, new PrintStream(outContent));

        counter.start();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

}