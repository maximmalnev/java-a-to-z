package ru.kovladimir.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ImprovedSortedSetTest {

    @Test
    public void whenAddToSetThenContainsIt() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");

        boolean result = set.contains("0");

        assertThat(result, is(true));
    }

    @Test
    public void whenAddValueThatAlreadyIsInSetThenDoNotAddIt() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");

        set.add("0");
        int size = set.size();

        assertThat(size, is(1));
    }

    @Test
    public void whenRemoveValueThanDoesNotContainIt() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");

        set.remove("0");
        boolean has = set.contains("0");

        assertThat(has, is(false));
    }

    @Test
    public void whenAddValueThenSetIsNotEmpty() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");

        boolean empty = set.isEmpty();

        assertThat(empty, is(false));
    }

    @Test
    public void whenRemoveAllValuesThenSetIsEmpty() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");
        set.add("1");

        set.remove("0");
        set.remove("1");
        boolean empty = set.isEmpty();

        assertThat(empty, is(true));
    }

    @Test
    public void whenAddTwoValuesThanSetSizeIsTwo() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");
        set.add("1");

        int size = set.size();

        assertThat(size, is(2));
    }

    @Test
    public void whenSetHasElementThenIteratorHasNext() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();

        boolean result = iterator.hasNext();

        assertThat(result, is(true));
    }

    @Test
    public void whenSetHasNoElementThenIteratorHasNotNext() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        Iterator<String> iterator = set.iterator();

        boolean result = iterator.hasNext();

        assertThat(result, is(false));
    }

    @Test
    public void whenSetHasOneElementThenIteratorHasNotNextAfterCallNext() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();
        iterator.next();

        boolean result = iterator.hasNext();

        assertThat(result, is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenSetHasNoElementThenIteratorThrowExceptionAfterCallNext() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        Iterator<String> iterator = set.iterator();
        iterator.next();

        iterator.next();
    }

    @Test(expected = IllegalStateException.class)
    public void whenRemoveBeforeGetNextThenThrowException() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();

        iterator.remove();
    }

    @Test
    public void whenRemoveAfterGetNextThenSetDoNotContainsElement() {
        ImprovedSortedSet<String> set = new ImprovedSortedSet<>();
        set.add("0");
        Iterator<String> iterator = set.iterator();

        iterator.next();
        iterator.remove();
        boolean has = set.contains("0");

        assertThat(has, is(false));
    }

}