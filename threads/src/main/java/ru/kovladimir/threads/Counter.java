package ru.kovladimir.threads;

import java.util.StringTokenizer;

/**
 * Counter.
 */
public class Counter {

    /**
     * First thread.
     */
    private Thread wordsThread;

    /**
     * Second thread.
     */
    private Thread whitespacesThread;

    /**
     * Was any of threads interrupted.
     */
    private boolean wasInterrupted = false;

    /**
     * Count oof words.
     */
    private int countWords = 0;

    /**
     * Count of whitespaces.
     */
    private int countWhiteSpaces = 0;

    /**
     * Count words and whitespaces in String.
     */
    public void start(String text, long timeForWaiting) {
        initThreads(text);

        System.out.println("Start.");
        long startTime = System.currentTimeMillis();

        startThreads();

        try {
            waitForThreads(timeForWaiting);
            if ((System.currentTimeMillis() - startTime) > timeForWaiting) {
                stopThreads();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printResult();
        System.out.println("Finish.");
    }

    /**
     * Init threads.
     *
     * @param text String.
     */
    private void initThreads(String text) {
        wordsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                StringTokenizer tokenizer = new StringTokenizer(text);
                while (tokenizer.hasMoreElements() && !Thread.currentThread().isInterrupted()) {
                    tokenizer.nextToken();
                    countWords++;
                }
                if (Thread.currentThread().isInterrupted()) {
                    wasInterrupted = true;
                }
            }
        });

        whitespacesThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < text.length() && !Thread.currentThread().isInterrupted(); i++) {
                    if (text.charAt(i) == ' ') {
                        countWhiteSpaces++;
                    }
                }
                if (Thread.currentThread().isInterrupted()) {
                    wasInterrupted = true;
                }
            }
        });
    }

    /**
     * Start both threads.
     */
    private void startThreads() {
        wordsThread.start();
        whitespacesThread.start();
    }

    /**
     * Wait for threads.
     *
     * @param timeForWaiting long. Time to wait.
     * @throws InterruptedException
     */
    private void waitForThreads(long timeForWaiting) throws InterruptedException {
        wordsThread.join(timeForWaiting);
        whitespacesThread.join(timeForWaiting);
    }

    /**
     * Interrupt both threads.
     *
     * @throws InterruptedException
     */
    private void stopThreads() throws InterruptedException {
        if (wordsThread.isAlive()) {
            wordsThread.interrupt();
        }
        if (whitespacesThread.isAlive()) {
            whitespacesThread.interrupt();
        }
        // wait until JVM interrupts threads. Shouldn't be long time.
        wordsThread.join();
        whitespacesThread.join();
    }

    /**
     * Print result.
     */
    private void printResult() {
        if (wasInterrupted) {
            System.out.println("The process lasted too long and was interrupted.");
        } else {
            System.out.printf("There are %d words.%n", countWords);
            System.out.printf("There are %d whitespaces.%n", countWhiteSpaces);
        }
    }
}
