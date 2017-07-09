package ru.kovladimir.threads.lists;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SynchronizedLinkedContainerTest {

    @Test
    public void whenAddElementThenContainerHasIt() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();

        container.add(1);
        boolean result = container.contains(1);

        assertThat(result, is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddInPositionBiggerThanSizeThenThrowException() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();

        container.add(6, 5);
    }

    @Test
    public void whenAddInValidPositionThanContainsIt() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        container.add(0, 2);
        int result = container.get(0);

        assertThat(result, is(2));
    }

    @Test
    public void whenAddInValidPositionThanShiftOldValueToNextPosition() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        container.add(0, 2);
        int result = container.get(1);

        assertThat(result, is(1));
    }

    @Test
    public void whenGetIndexOfAddedElementThenReturnIt() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        int result = container.indexOf(1);

        assertThat(result, is(0));
    }

    @Test
    public void whenSetNewElementThenContainsItInThisPosition() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        container.set(0, 2);
        int result = container.get(0);

        assertThat(result, is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenClearContainerThenItHasNoElements() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        container.clear();
        int result = container.get(0);
    }

    @Test
    public void whenRemoveByIndexThenContainerDoesNotHaveIt() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        container.remove(0);
        boolean result = container.contains(1);

        assertThat(result, is(false));
    }

    @Test
    public void whenRemoveElementThenNextElementIsInHisPosition() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);
        container.add(2);

        container.remove(0);
        int position = container.indexOf(2);

        assertThat(position, is(0));
    }

    @Test
    public void whenRemoveByIndexInMiddleThenContainerDoesNotHaveIt() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);

        container.remove(2);
        boolean result = container.contains(3);

        assertThat(result, is(false));
    }

    @Test
    public void whenRemoveByIndexInMiddleThenNextElementIsInHisPosition() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);

        container.remove(2);
        int position = container.indexOf(4);

        assertThat(position, is(2));
    }

    @Test
    public void whenRemoveByObjectThenContainerDoesnotHaveIt() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        container.remove(new Integer(1));
        boolean result = container.contains(1);

        assertThat(result, is(false));
    }

    @Test
    public void whenContainerHasOneElementThenReturnOne() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);

        int size = container.size();

        assertThat(size, is(1));
    }

    @Test
    public void whenContainerHasTwoElementsThenReturnTwo() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);
        container.add(1);

        int size = container.size();

        assertThat(size, is(2));
    }

    @Test
    public void whenRemoveElementThenSizeMustDecrement() {
        SynchronizedSimpleContainer<Integer> container = new SynchronizedLinkedContainer<>();
        container.add(1);
        container.add(1);

        container.remove(1);
        int size = container.size();

        assertThat(size, is(1));
    }

}