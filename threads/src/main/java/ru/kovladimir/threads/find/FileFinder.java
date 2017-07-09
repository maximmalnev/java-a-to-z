package ru.kovladimir.threads.find;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Finder.
 */
public class FileFinder {
    /**
     * Params.
     */
    private Params params;

    /**
     * Stop flag.
     */
    private AtomicBoolean stop = new AtomicBoolean(false);

    /**
     * Queue of files.
     */
    private BlockingQueue<File> queue;

    /**
     * Found find. Result.
     */
    private File foundFile = null;

    /**
     * Constructor.
     * @param params Params.
     */
    public FileFinder(Params params) {
        this.params = params;
    }

    /**
     * Start. Main method.
     */
    public void start() {
        if (params.isValid()) {
            findFileByName(params.getStartDirectory(), params.getFileName());
            printResult();
        } else {
            System.err.println("Not valid args!");
        }
    }

    /**
     * Find file by name.
     * @param startDirectory File.
     * @param fileName String.
     */
    private void findFileByName(File startDirectory, String fileName) {
        queue = new LinkedBlockingQueue<>();
        queue.add(startDirectory);
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(cores, cores,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());

        while (!stop.get()) {
            Runnable task = new FileRunnable(fileName);
            executor.submit(task);

            if (executor.getActiveCount() == 0 && queue.isEmpty()) {
                stop.set(true);
            }
        }

        executor.shutdownNow();
    }

    /**
     * Print result.
     */
    private void printResult() {
        if (foundFile == null) {
            System.out.println("File was not found.");
        } else {
            System.out.printf("File is found!%n%s", foundFile.getAbsolutePath());
        }
    }

    /**
     * Runnable to find file.
     */
    private class FileRunnable implements Runnable {

        /**
         * File name to find.
         */
        private String fileName;

        /**
         * Constructor.
         * @param fileName String.
         */
        public FileRunnable(String fileName) {
            this.fileName = fileName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            File fileFromQueue = queue.remove();
            if (fileFromQueue.isDirectory()) {
                Collections.addAll(queue, fileFromQueue.listFiles());
            } else {
                if (fileFromQueue.getName().equals(fileName)) {
                    foundFile = fileFromQueue;
                    stop.set(true);
                }
            }
        }
    }

    public static void main(String[] args) {
        Params params = new ParamsImpl(args);
        FileFinder finder = new FileFinder(params);
        finder.start();
    }
}
