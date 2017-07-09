package ru.kovladimir.threads.lists;

import ru.kovladimir.list.ArrayContainer;

/**
 * Synchronized Array Container
 * @param <E>
 */
public class SynchronizedArrayContainer<E> extends SynchronizedSimpleContainer<E> {

    /**
     * Constructor.
     */
    public SynchronizedArrayContainer() {
        super(new ArrayContainer<>());
    }

    /**
     * Constructor with size.
     * @param size int.
     */
    public SynchronizedArrayContainer(int size) {
        super(new ArrayContainer<>(size));
    }
}
