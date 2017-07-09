package ru.kovladimir.threads;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CounterTest {

    @Test
    public void whenCountThenOutputFourSpacesAndFiveWords() {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Counter counter = new Counter();

        counter.start("This is a test line.", 1000);
        String content = outContent.toString();
        boolean result = (content.equals(String.format("Start.%nThere are %d whitespaces.%nThere are %d words.%nFinish.%n", 4, 5))) ||
                content.equals(String.format("Start.%nThere are %d words.%nThere are %d whitespaces.%nFinish.%n", 5, 4));
        System.setOut(oldOut);

        assertThat(result, is(true));
    }

    @Test
    public void whenCountThenOutputEightSpacesAndSixWords()  {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Counter counter = new Counter();

        counter.start("   Hello, this is a test line.", 1000);
        String content = outContent.toString();
        boolean result = (content.equals(String.format("Start.%nThere are %d whitespaces.%nThere are %d words.%nFinish.%n", 8, 6))) ||
                content.equals(String.format("Start.%nThere are %d words.%nThere are %d whitespaces.%nFinish.%n", 6, 8));
        System.setOut(oldOut);


        assertThat(result, is(true));
    }

    @Test
    public void whenTooLongThenOutputMessage() {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Counter counter = new Counter();

        counter.start(generateBigString(), 1);
        String content = outContent.toString();
        System.setOut(oldOut);


        assertThat(content, is(String.format("Start.%nThe process lasted too long and was interrupted.%nFinish.%n")));
    }

    private String generateBigString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            builder.append("12 124 13413 13 3y6456y 265y21 ");
        }
        return builder.toString();
    }

}