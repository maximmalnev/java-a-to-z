package ru.kovladimir.part4.task3;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class SmallestOfThreeCounterTest {

    @Test
    public void whenAllIntThenPrintSmallest() throws IOException {
        String separator = System.lineSeparator();
        String first = "-1";
        String second = "20";
        String third = "3";
        String message = "-1" + separator;
        ByteArrayInputStream inContent = new ByteArrayInputStream((first + separator + second +
                separator + third).getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        SmallestOfThreeCounter counter = new SmallestOfThreeCounter(inContent, new PrintStream(outContent));

        counter.start();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenOneNotIntThenPrintError() throws IOException {
        String separator = System.lineSeparator();
        String first = "-1";
        String second = "fail";
        String third = "3";
        String message = "This is not integer." + separator;
        ByteArrayInputStream inContent = new ByteArrayInputStream((first + separator + second +
                separator + third).getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        SmallestOfThreeCounter counter = new SmallestOfThreeCounter(inContent, new PrintStream(outContent));

        counter.start();
        inContent.close();
        outContent.close();

        assertEquals(message, outContent.toString());
    }

}