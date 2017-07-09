package ru.kovladimir.list.circle;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CheckerTest {

    @Test
    public void whenNotCyclicReturnFalse() {
        Checker checker = new Checker(new NotCyclicLinkedList(
                new Integer[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}));

        boolean result = checker.isCyclic();

        assertThat(result, is(false));
    }

    @Test
    public void whenCyclicReturnTrue() {
        Checker checker = new Checker(new CyclicLinkedList(
                new Integer[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}));

        boolean result = checker.isCyclic();

        assertThat(result, is(true));
    }

}