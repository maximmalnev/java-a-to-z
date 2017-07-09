package ru.kovladimir.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QueueContainerTest {

    @Test
    public void whenQueueElementThenContainsIt() {
        QueueContainer<String> container = new QueueContainer<>();

        container.queue("Hello");
        String result = container.dequeue();

        assertThat(result, is("Hello"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDequeueEmptyStackThenThrowException() {
        QueueContainer<String> container = new QueueContainer<>();

        container.dequeue();
    }

    @Test
    public void whenCheckEmptyQueueThenReturnTrue() {
        QueueContainer<String> container = new QueueContainer<>();

        boolean result = container.isEmpty();

        assertThat(result, is(true));
    }

    @Test
    public void whenCheckNotEmptyQueueThenReturnFalse() {
        QueueContainer<String> container = new QueueContainer<>();
        container.queue("Hello");

        boolean result = container.isEmpty();

        assertThat(result, is(false));
    }

    @Test
    public void whenDequeueThenReturnOldestElement() {
        QueueContainer<String> container = new QueueContainer<>();
        container.queue("Hello");
        container.queue("Goodbye");

        String result = container.dequeue();

        assertThat(result, is("Hello"));
    }

}