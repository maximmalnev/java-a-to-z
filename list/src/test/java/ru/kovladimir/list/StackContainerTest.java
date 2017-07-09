package ru.kovladimir.list;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StackContainerTest {

    @Test
    public void whenPushElementThenContainsIt() {
        StackContainer<String> container = new StackContainer<>();

        container.push("Hello");
        String result = container.peek();

        assertThat(result, is("Hello"));
    }

    @Test(expected = EmptyStackException.class)
    public void whenPopEmptyStackThenThrowException() {
        StackContainer<String> container = new StackContainer<>();

        container.pop();
    }

    @Test
    public void whenPeekThenDoNotRemoveElement() {
        StackContainer<String> container = new StackContainer<>();
        container.push("Hello");

        container.peek();
        container.peek();
        String result = container.pop();

        assertThat(result, is("Hello"));
    }

    @Test
    public void whenCheckEmptyStackThenReturnTrue() {
        StackContainer<String> container = new StackContainer<>();

        boolean result = container.isEmpty();

        assertThat(result, is(true));
    }

    @Test
    public void whenCheckNotEmptyStackThenReturnFalse() {
        StackContainer<String> container = new StackContainer<>();
        container.push("Hello");

        boolean result = container.isEmpty();

        assertThat(result, is(false));
    }

    @Test
    public void whenPopThenReturnNewestElement() {
        StackContainer<String> container = new StackContainer<>();
        container.push("Hello");
        container.push("Goodbye");

        String result = container.pop();

        assertThat(result, is("Goodbye"));
    }
}