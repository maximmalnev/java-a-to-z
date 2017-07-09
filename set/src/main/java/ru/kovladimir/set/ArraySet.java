package ru.kovladimir.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Array set.
 * @param <E>
 */
public class ArraySet<E> implements SimpleSet<E> {

    /**
     * Default size.
     */
    protected static final int AMOUNT_TO_EXTEND_ARRAY = 2;

    /**
     * Amount to extend array if there are no available places to add.
     */
    protected static final int DEFAULT_SIZE = 10;

    /**
     * Elements.
     */
    private Object[] elements;

    /**
     * Size ('cursor') of the elements.
     */
    private int size = 0;

    /**
     * Constructor with default size.
     */
    public ArraySet() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor with size.
     * @param size int.
     */
    public ArraySet(int size) {
        elements = new Object[size];
    }

    /**
     * {@inheritDoc}
     * @param element
     * @return
     */
    @Override
    public boolean add(E element) {
        boolean wasAdded = false;
        if (!contains(element)) {
            if (!hasPlaceToAdd()) {
                extendArray();
            }
            elements[size++] = element;
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     * @param o Object.
     * @return
     */
    @Override
    public boolean remove(Object o) {
        boolean wasRemoved = false;
        int index = indexOf(o);
        if (index != -1) {
            System.arraycopy(elements, index + 1, elements, index, elements.length - 1 - index);
            elements[--size] = null;
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     * @param o Object.
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new ArraySetIterator();
    }

    /**
     * Check if there is at least one place to add element.
     *
     * @return boolean.
     */
    private boolean hasPlaceToAdd() {
        return elements.length > size;
    }

    /**
     * Extend array and save all elements in new array.
     */
    private void extendArray() {
        elements = Arrays.copyOf(elements, elements.length * AMOUNT_TO_EXTEND_ARRAY);
    }

    /**
     * {@inheritDoc}
     *
     * @param element Object.
     * @return
     */
    private int indexOf(Object element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Iterator.
     */
    private class ArraySetIterator implements Iterator<E> {

        /**
         * Index of next element.
         */
        private int nextIndex = 0;

        private int prevIndex = -1;

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        /**
         * {@inheritDoc}
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                prevIndex = nextIndex;
                return (E) elements[nextIndex++];
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (prevIndex == -1) {
                throw new IllegalStateException();
            }
            ArraySet.this.remove(elements[prevIndex]);
            prevIndex = -1;
        }
    }
}
