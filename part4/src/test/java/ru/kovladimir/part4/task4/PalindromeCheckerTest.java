package ru.kovladimir.part4.task4;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class PalindromeCheckerTest {

    @Test
    public void whenPalindromeThenPrintSuccess() throws IOException {
        PalindromeChecker checker = new PalindromeChecker();
        String separator = System.lineSeparator();
        String word = "qwewq";
        String message = "The word is palindrome." + separator;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        checker.isPalindrome(word, new PrintStream(outContent));
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenNotPalindromeThenPrintError() throws IOException {
        PalindromeChecker checker = new PalindromeChecker();
        String separator = System.lineSeparator();
        String word = "qwert";
        String message = "The word is not palindrome." + separator;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        checker.isPalindrome(word, new PrintStream(outContent));
        outContent.close();

        assertEquals(message, outContent.toString());
    }

    @Test
    public void whenLengthIsNotFiveThenPrintError() throws IOException {
        PalindromeChecker checker = new PalindromeChecker();
        String separator = System.lineSeparator();
        String word = "Eve";
        String message = "The length of the word is not 5 characters." + separator;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        checker.isPalindrome(word, new PrintStream(outContent));
        outContent.close();

        assertEquals(message, outContent.toString());
    }

}