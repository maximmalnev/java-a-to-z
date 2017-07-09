package ru.kovladimir.threads;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CountTest {

    @Test
    public void whenIncrementTest1ReturnRightValue() throws InterruptedException {
        Count.Count1 count1 = new Count.Count1();
        Thread thread1 = new Thread(new IncrementRunnable(count1));
        Thread thread2 = new Thread(new IncrementRunnable(count1));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int result = count1.count;

        assertThat(result, is(2));
    }

    @Test
    public void whenIncrementTest2ReturnRightValue() throws InterruptedException {
        Count.Count2 count2 = new Count.Count2();
        Thread thread1 = new Thread(new IncrementRunnable(count2));
        Thread thread2 = new Thread(new IncrementRunnable(count2));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int result = count2.count;

        assertThat(result, is(2));
    }

    @Test
    public void whenIncrementTest3ReturnRightValue() throws InterruptedException {
        Count.Count3 count3 = new Count.Count3();
        Thread thread1 = new Thread(new IncrementRunnable(count3));
        Thread thread2 = new Thread(new IncrementRunnable(count3));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int result = count3.count;

        assertThat(result, is(2));
    }

    @Test
    public void whenIncrementTest4ReturnRightValue() throws InterruptedException {
        Count.Count4 count4 = new Count.Count4();
        Thread thread1 = new Thread(new IncrementRunnable(count4));
        Thread thread2 = new Thread(new IncrementRunnable(count4));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int result = count4.count.get();

        assertThat(result, is(2));
    }

    @Test
    public void whenIncrementTest5ReturnNull() throws InterruptedException {
        Count.Count5 count5 = new Count.Count5();
        Thread thread1 = new Thread(new IncrementRunnable(count5));
        Thread thread2 = new Thread(new IncrementRunnable(count5));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Integer result = count5.count.get();

        assertNull(result);
    }

    private static class IncrementRunnable implements Runnable {

        Count.CountInterface count;

        public IncrementRunnable(Count.CountInterface count) {
            this.count = count;
        }

        @Override
        public void run() {
            count.increment();
        }
    }


}