package ru.kovladimir.iterators;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenNumberIteratorTest {

    @Test
    public void whenCallNextThenReturnFirstEvenValue() {
        Iterator iterator = new EvenNumberIterator(new int[] {1, 2, 3});

        int result = (int) iterator.next();

        assertThat(result, is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoEvenAndCallNextThenThrowException() {
        Iterator iterator = new EvenNumberIterator(new int[] {1, 3, 5});

        int result = (int) iterator.next();
    }

    @Test
    public void whenCallHasNextAndNextThenReturnEvenValues() {
        Iterator iterator = new EvenNumberIterator(new int[] {1, 2, 3, 4});
        List list = new ArrayList(Arrays.asList(2, 4));
        List result = new ArrayList();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result, is(list));
    }
}