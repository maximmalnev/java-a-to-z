package ru.kovladimir.threads.prodconsm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Demonstration of Producer-Consumer.
 */
public class Demo {

    /**
     * Queue of elements.
     */
    public final Queue<Integer> queue = new LinkedList<>();

    /**
     * Start demo.
     */
    public void start() {
        Thread producer = new Thread(new Producer(), "Producer");
        Thread consumer1 = new Thread(new Consumer(), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(), "Consumer-2");
        consumer1.setDaemon(true);
        consumer2.setDaemon(true);

        producer.start();
        consumer1.start();
        consumer2.start();
    }

    /**
     * Add element.
     * @param i Integer.
     */
    private void add(Integer i) {
        synchronized (queue) {
            queue.add(i);
            System.out.printf("%s has added %d%n", Thread.currentThread().getName(), i);
            queue.notifyAll();
        }
    }

    /**
     * Get element.
     * @throws InterruptedException
     */
    private void get() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            System.out.printf("%s has taken %d%n", Thread.currentThread().getName(), queue.remove());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        demo.start();
    }

    /**
     * Producer.
     */
    private class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                add(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Consumer.
     */
    private class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
