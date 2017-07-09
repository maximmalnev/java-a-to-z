package ru.kovladimir.threads.pool;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void testOneCore() throws InterruptedException {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ThreadPool pool = new ThreadPool(1);
        pool.add(new Runnable() {
            @Override
            public void run() {
                System.out.println("test1");
            }
        });
        pool.add(new Runnable() {
            @Override
            public void run() {
                System.out.println("test2");
            }
        });
        Thread.sleep(100);
        pool.shutdown();
        String result = outContent.toString();
        System.setOut(oldOut);

        assertThat(result, is(String.format("test1%ntest2%n")));
    }


}