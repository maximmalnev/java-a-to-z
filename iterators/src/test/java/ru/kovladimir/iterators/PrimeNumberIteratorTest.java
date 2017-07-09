package ru.kovladimir.iterators;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PrimeNumberIteratorTest {

    @Test
    public void whenCallNextThenReturnFirstPrimeValue() {
        Iterator iterator = new PrimeNumberIterator(new int[] {1, 2, 3});

        int result = (int) iterator.next();

        assertThat(result, is(1));
    }

    @Test
    public void whenCallNextThenReturnSecondPrimeValue() {
        Iterator iterator = new PrimeNumberIterator(new int[] {1, 2, 3});

        iterator.next();
        int result = (int) iterator.next();

        assertThat(result, is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoEvenAndCallNextThenThrowException() {
        Iterator iterator = new PrimeNumberIterator(new int[] {4, 6, 10});

        int result = (int) iterator.next();
    }

    @Test
    public void whenCallHasNextAndNextThenReturnEvenValues() {
        Iterator iterator = new PrimeNumberIterator(new int[] {1, 5, 7, 10, 16});
        List list = new ArrayList(Arrays.asList(1, 5, 7));
        List result = new ArrayList();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result, is(list));
    }

}