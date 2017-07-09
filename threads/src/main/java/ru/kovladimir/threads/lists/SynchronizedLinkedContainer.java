package ru.kovladimir.threads.lists;

import ru.kovladimir.list.LinkedContainer;

/**
 * Synchronized Linked Container
 * @param <E>
 */
public class SynchronizedLinkedContainer<E> extends SynchronizedSimpleContainer<E> {

    /**
     * Constructor.
     */
    public SynchronizedLinkedContainer() {
        super(new LinkedContainer<>());
    }

}
