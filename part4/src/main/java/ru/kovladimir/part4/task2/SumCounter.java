package ru.kovladimir.part4.task2;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Sum's counter.
 */
public class SumCounter {

    /**
     * Scanner to scan number.
     */
    private Scanner scanner;

    /**
     * PrintStream to print result.
     */
    private PrintStream printStream;

    /**
     * Result.
     */
    private int result = 0;

    /**
     * Constructor.
     * @param inputStream InputStream.
     * @param printStream PrintStream.
     */
    public SumCounter(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    /**
     * Count sum.
     */
    public void start() {
        try {
            for (int i = 0; i < 2; i++) {
                addToResult(Integer.parseInt(askString()));
            }
            printResult(String.valueOf(result));
        } catch (NumberFormatException e) {
            printResult("This is not integer.");
        } finally {
            closeStream();
        }
    }

    /**
     * Add number to result
     * @param number int.
     */
    private void addToResult(int number) {
        result += number;
    }

    /**
     * Get line from input.
     * @return String.
     */
    private String askString() {
        return scanner.nextLine();
    }

    /**
     * Print result.
     * @param message String.
     */
    private void printResult(String message) {
        printStream.println(message);
    }

    /**
     * Close stream.
     */
    private void closeStream() {
        scanner.close();
    }

    public static void main(String[] args) {
        SumCounter sumCounter = new SumCounter(System.in, System.out);
        sumCounter.start();
    }
}
