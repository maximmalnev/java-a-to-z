package ru.kovladimir.part4.task4;

import java.io.PrintStream;

/**
 * Check palindrome.
 */
public class PalindromeChecker {

    /**
     * Check is string palindrome.
     * @param word String.
     * @param printStream PrintStream.
     */
    public void isPalindrome(String word, PrintStream printStream) {
        if (word == null || word.length() != 5) {
            printStream.println("The length of the word is not 5 characters.");
        } else if (word.equalsIgnoreCase(new StringBuilder(word).reverse().toString())) {
            printStream.println("The word is palindrome.");
        } else {
            printStream.println("The word is not palindrome.");
        }
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        checker.isPalindrome("РОТОР", System.out);
    }

}
