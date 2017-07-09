package ru.kovladimir.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Queue container.
 */
public class QueueContainer<E> implements SimpleQueue<E> {

    private LinkedContainer<E> container = new LinkedContainer<>();

    /**
     * {@inheritDoc}
     *
     * @param element E.
     */
    @Override
    public void queue(E element) {
        container.add(element);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (container.size() == 0) {
            throw new NoSuchElementException();
        }
        Iterator<E> iterator = container.iterator();
        E element = iterator.next();
        iterator.remove();
        return element;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return container.size() == 0;
    }
}
