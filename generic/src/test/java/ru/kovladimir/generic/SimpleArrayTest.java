package ru.kovladimir.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddValueThenArrayContainsIt() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);

        Integer result = array.get(0);

        assertThat(result, is(1));
    }

    @Test
    public void whenDeleteValueThenArrayDoesNotContainIt() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);

        array.delete(0);
        Integer result = array.get(0);

        assertNull(result);
    }

    @Test
    public void whenUpdateValueThenArrayContainsNewValue() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(1);

        array.update(0, 2);
        Integer result = array.get(0);

        assertThat(result, is(2));
    }

}