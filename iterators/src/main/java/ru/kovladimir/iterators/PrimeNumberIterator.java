package ru.kovladimir.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator of prime numbers.
 */
public class PrimeNumberIterator implements Iterator {

    /**
     * True if there was a check of existence next valid value.
     */
    private boolean nextValidValueWasChecked = false;

    /**
     * Next position of valid value (if there was a check.
     */
    private int nextValidPosition = -1;

    /**
     * Cursor of array.
     */
    private int cursor = 0;

    /**
     * Array with numbers.
     */
    private int[] values;

    /**
     * Constructor.
     * @param values int[].
     */
    public PrimeNumberIterator(int[] values) {
        this.values = values;
    }

    /**
     * Check if there is one or more valid values after cursor.
     * @return boolean.
     */
    @Override
    public boolean hasNext() {
        boolean has = false;
        for (int i = cursor; i < values.length; i++) {
            if (isPrimeNumber(values[i])) {
                nextValidPosition = i;
                nextValidValueWasChecked = true;
                has = true;
                break;
            }
        }
        return has;
    }

    /**
     * Return next valid value or throw Exception.
     * @return Object.
     */
    @Override
    public Object next() {
        if (cursor >= values.length) {
            throw new NoSuchElementException();
        }
        Object result = getNextValue();
        nextValidValueWasChecked = false;
        return result;
    }

    /**
     * Get next valid value. If there was check (call method hasNext()) before than get saved value.
     * Else find valid value and return it.
     */
    private Object getNextValue() {
        Object result = null;
        if (nextValidValueWasChecked) {
            result = values[nextValidPosition];
            cursor = nextValidPosition + 1;
        } else {
            for (int i = cursor; i < values.length; i++) {
                if (isPrimeNumber(values[i])) {
                    result = values[i];
                    cursor = i + 1;
                    break;
                }
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Check if number is prime.
     * @param number int.
     * @return boolean.
     */
    private boolean isPrimeNumber(int number) {
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
