package ru.kovladimir.part4.task3;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Finder the smallest number.
 */
public class SmallestOfThreeCounter {

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
    private int result = Integer.MAX_VALUE;

    /**
     * Constructor.
     * @param inputStream InputStream.
     * @param printStream PrintStream.
     */
    public SmallestOfThreeCounter(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    /**
     * Read and find the smallest.
     */
    public void start() {
        try {
            for (int i = 0; i < 3; i++) {
                int number = Integer.parseInt(askString());
                checkSmallest(number);
            }
            printResult(String.valueOf(result));
        } catch (NumberFormatException e) {
            printResult("This is not integer.");
        } finally {
            closeStream();
        }
    }

    /**
     * Check if number is smaller than result.
     * @param number int.
     */
    private void checkSmallest(int number) {
        result = number < result ? number : result;
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
        SmallestOfThreeCounter counter = new SmallestOfThreeCounter(System.in, System.out);
        counter.start();
    }

}
