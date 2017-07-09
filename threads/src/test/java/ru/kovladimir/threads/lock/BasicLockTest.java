package ru.kovladimir.threads.lock;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BasicLockTest {

    @Test
    public void whenTwoThreadsThenOneThreadStartsAfterAnotherHasFinished() throws InterruptedException {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BasicLock lock = new BasicLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%d, ", 1);
                }
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%d, ", 2);
                }
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.setOut(oldOut);
        String content = outContent.toString();
        boolean result = "1, 1, 1, 1, 1, 2, 2, 2, 2, 2, ".equals(content) || "2, 2, 2, 2, 2, 1, 1, 1, 1, 1, ".equals(content);

        assertThat(result, is(true));
    }

}