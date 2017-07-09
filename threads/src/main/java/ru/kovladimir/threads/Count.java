package ru.kovladimir.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Count.
 */
public class Count {

    /**
     * Count interface.
     */
    public interface CountInterface {

        /**
         * Increment value.
         */
        void increment();
    }

    /**
     * Count1.
     */
    public static class Count1 implements CountInterface {

        /**
         * Value.
         */
        int count = 0;

        /**
         * {@inheritDoc}
         */
        @Override
        public void increment() {
            synchronized (Count1.class) {
                count++;
            }
        }
    }

    /**
     * Count2.
     */
    public static class Count2 implements CountInterface {

        /**
         * Value.
         */
        int count = 0;

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void increment() {
            count++;
        }
    }

    /**
     * Count3.
     */
    public static class Count3 implements CountInterface {

        /**
         * Value.
         */
        int count = 0;

        /**
         * Lock.
         */
        final Object lock = new Object();

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void increment() {
            synchronized (lock) {
                count++;
            }
        }
    }

    /**
     * Count4.
     */
    public static class Count4 implements CountInterface {

        /**
         * Value.
         */
        AtomicInteger count = new AtomicInteger();

        /**
         * {@inheritDoc}
         */
        @Override
        public synchronized void increment() {
            count.incrementAndGet();
        }
    }

    /**
     * Count5.
     */
    public static class Count5 implements CountInterface {

        /**
         * Value.
         */
        ThreadLocal<Integer> count = new ThreadLocal<>();

        /**
         * {@inheritDoc}
         */
        @Override
        public void increment() {
            if (count.get() == null) {
                count.set(1);
            } else {
                count.set(count.get() + 1);
            }
        }
    }

}
