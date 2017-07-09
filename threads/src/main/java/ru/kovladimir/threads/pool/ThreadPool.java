package ru.kovladimir.threads.pool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Thread Pool.
 */
public class ThreadPool {

    /**
     * Queue of tasks.
     */
    private final Queue<Runnable> queue = new LinkedList<>();

    /**
     * Threads to run tasks.
     */
    private Thread[] threads;

    /**
     * Lock.
     */
    private final Object lock = new Object();

    /**
     * Close-flag.
     */
    private boolean closed = false;

    /**
     * Constructor with processors cores.
     */
    public ThreadPool() {
        this(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Constructor with custom amount of threads.
     * @param cores int.
     */
    public ThreadPool(int cores) {
        threads = new Thread[cores];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PoolRunnable());
            threads[i].start();
        }
    }

    /**
     * Add task to Thread Pool.
     * @param runnable Runnable.
     */
    public void add(Runnable runnable) {
        synchronized (lock) {
            if (!closed) {
                queue.add(runnable);
                lock.notifyAll();
            }
        }
    }

    /**
     * Shut Down Thread Pool.
     */
    public void shutdown() {
        closed = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    /**
     * Pool Runnable to run in threads.
     */
    private class PoolRunnable implements Runnable {
        @Override
        public void run() {

            // try get task and run it until pool is closed or thread is interrupted
            while (!closed) {

                // wait if there is no tasks
                synchronized (lock) {
                    while (queue.isEmpty() && !closed) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }

                }

                // if pool is not closed then try to get task and run it
                if (!closed) {
                    Runnable task;
                    synchronized (queue) {
                        task = queue.poll();
                    }
                    if (task != null) {
                        task.run();
                    }
                }
            }
        }
    }



}
