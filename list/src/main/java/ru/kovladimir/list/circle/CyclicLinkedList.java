package ru.kovladimir.list.circle;

import java.util.Iterator;

/**
 * Cyclic Linked List
 */
public class CyclicLinkedList implements Iterable, SimpleLinkedList {

    /**
     * Array.
     */
    private Object[] objects;

    /**
     * Constructor.
     *
     * @param objects Object[].
     */
    public CyclicLinkedList(Object[] objects) {
        this.objects = objects;
    }

    /**
     * {@inheritDoc}
     *
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
             * @return
             */
            @Override
            public boolean hasNext() {
                return true;
            }

            /**
             * {@inheritDoc}
             * @return
             */
            @Override
            public Object next() {
                if (position >= objects.length) {
                    position = 0;
                }
                return objects[position++];
            }
        };
    }
}
