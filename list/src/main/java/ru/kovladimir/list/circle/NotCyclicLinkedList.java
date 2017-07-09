package ru.kovladimir.list.circle;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Not Cyclic Linked List
 */
public class NotCyclicLinkedList implements Iterable, SimpleLinkedList {

    /**
     * Array.
     */
    private Object[] objects;

    /**
     * Constructor.
     * @param objects Object[].
     */
    public NotCyclicLinkedList(Object[] objects) {
        this.objects = objects;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {

            /**
             * Position.
             */
            private int position = 0;

            /**
             * {@inheritDoc}
             *
             * @return
             */
            @Override
            public boolean hasNext() {
                return position < objects.length;
            }

            /**
             * {@inheritDoc}
             * @return
             */
            @Override
            public Object next() {
                if (position >= objects.length) {
                    throw new NoSuchElementException();
                }
                return objects[position++];
            }
        };
    }
}
