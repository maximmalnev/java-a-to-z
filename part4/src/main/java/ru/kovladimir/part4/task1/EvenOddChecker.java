package ru.kovladimir.part4.task1;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Check is number even or odd.
 */
public class EvenOddChecker {

    /**
     * Scanner to scan number.
     */
    private Scanner scanner;

    /**
     * PrintStream to print result.
     */
    private PrintStream printStream;

    /**
     * Constructor.
     * @param inputStream InputStream.
     * @param printStream PrintStream.
     */
    public EvenOddChecker(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    /**
     * Main logic.
     */
    public void check() {
        try {
            int number = Integer.parseInt(askString());
            if (isEven(number)) {
                printResult("This is even number.");
            } else {
                printResult("This is odd number.");
            }
        } catch (NumberFormatException e) {
            printResult("This is not integer.");
        } finally {
            closeStream();
        }
    }

    /**
     * Get line from input.
     * @return String.
     */
    private String askString() {
        return scanner.nextLine();
    }

    /**
     * Is number even or odd.
     * @param number int.
     * @return boolean.
     */
    private boolean isEven(int number) {
        return number % 2 == 0;
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
        EvenOddChecker checker = new EvenOddChecker(System.in, System.out);
        checker.check();
    }

}
