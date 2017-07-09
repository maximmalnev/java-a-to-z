package ru.kovladimir.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ImprovedSetTest {

    @Test
    public void whenAddToSetThenContainsIt() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");

        boolean result = set.contains("0");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddValueThatAlreadyIsInSetThenDoNotAddIt() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");

        set.add("0");
        int size = set.size();

        assertThat(size, is(1));
    }

    @Test
    public void whenRemoveValueThanDoesNotContainIt() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");

        set.remove("0");
        boolean has = set.contains("0");

        assertThat(has, is(false));
    }

    @Test
    public void whenAddValueThenSetIsNotEmpty() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");

        boolean empty = set.isEmpty();

        assertThat(empty, is(false));
    }

    @Test
    public void whenRemoveAllValuesThenSetIsEmpty() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");
        set.add("1");

        set.remove("0");
        set.remove("1");
        boolean empty = set.isEmpty();

        assertThat(empty, is(true));
    }

    @Test
    public void whenAddTwoValuesThanSetSizeIsTwo() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");
        set.add("1");

        int size = set.size();

        assertThat(size, is(2));
    }

    @Test
    public void whenSetHasElementThenIteratorHasNext() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();

        boolean result = iterator.hasNext();

        assertThat(result, is(true));
    }

    @Test
    public void whenSetHasNoElementThenIteratorHasNotNext() {
        SimpleSet<String> set = new ImprovedSet<>();
        Iterator<String> iterator = set.iterator();

        boolean result = iterator.hasNext();

        assertThat(result, is(false));
    }

    @Test
    public void whenSetHasOneElementThenIteratorHasNotNextAfterCallNext() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();
        iterator.next();

        boolean result = iterator.hasNext();

        assertThat(result, is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSetHasNoElementThenIteratorThrowExceptionAfterCallNext() {
        SimpleSet<String> set = new ImprovedSet<>();
        Iterator<String> iterator = set.iterator();
        iterator.next();

        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void whenRemoveBeforeGetNextThenThrowException() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();

        iterator.remove();
    }

    @Test
    public void whenRemoveAfterGetNextThenSetDoNotContainsElement() {
        SimpleSet<String> set = new ImprovedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();

        iterator.next();
        iterator.remove();
        boolean has = set.contains("0");

        assertThat(has, is(false));
    }

}