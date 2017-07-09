package ru.kovladimir.list;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Stack container.
 * @param <E>
 */
public class StackContainer<E> implements SimpleStack<E> {

    /**
     * Container.
     */
    private LinkedContainer<E> container = new LinkedContainer<>();

    /**
     * {@inheritDoc}
     * @param element E.
     */
    @Override
    public void push(E element) {
        container.add(element);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public E pop() {
        checkSize();
        Iterator<E> iterator = container.iteratorBackward();
        E element = iterator.next();
        iterator.remove();
        return element;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public E peek() {
        checkSize();
        Iterator<E> iterator = container.iteratorBackward();
        return iterator.next();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isEmpty() {
        return container.size() == 0;
    }

    /**
     * Check size of container.
     * If 0 then throw EmptyStackException.
     */
    private void checkSize() {
        if (container.size() == 0) {
            throw new EmptyStackException();
        }
    }
}
