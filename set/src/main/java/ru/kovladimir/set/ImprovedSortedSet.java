package ru.kovladimir.set;

import java.util.*;

/**
 * Improved set with binary search.
 * @param <E>
 */
public class ImprovedSortedSet<E> implements SimpleSet<E> {

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
    public ImprovedSortedSet() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor with size.
     *
     * @param size int.
     */
    public ImprovedSortedSet(int size) {
        elements = new Object[size];
    }

    /**
     * {@inheritDoc}
     *
     * @param element
     * @return
     */
    @Override
    public boolean add(E element) {
        boolean wasAdded = false;
        int position = findPositionForAddition(element);
        if (position != -1) {
            if (!hasPlaceToAdd()) {
                extendArray();
            }
            System.arraycopy(elements, position, elements, position + 1, size - position);
            elements[position] = element;
            size++;
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Object.
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        boolean wasRemoved = false;
        if (o instanceof Comparable) {
            E element = (E) o;
            int position = findPositionOfElement(element);
            if (position != -1) {
                System.arraycopy(elements, position + 1, elements, position, elements.length - 1 - position);
                elements[--size] = null;
                wasRemoved = true;
            }
        }
        return wasRemoved;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Object.
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        boolean contains = false;
        if (o instanceof Comparable) {
            E element = (E) o;
            if (findPositionOfElement(element) != -1) {
                contains = true;
            }
        }
        return contains;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new ImprovedSortedSetIterator();
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
     * Find position to add element.
     * If array already has this element then return -1 (no place for addition because it is set).
     * If array don't have element then return position where this element should be places.
     * @param element E.
     * @return int.
     */
    @SuppressWarnings("unchecked")
    private int findPositionForAddition(E element) {
        int position;
        int low = 0;
        int high = size - 1;
        while (true) {
            if (low > high) {
                position = low;
                break;
            }
            int middle = (low + high) / 2;
            E middleElement = (E) elements[middle];
            if (middleElement.hashCode() < element.hashCode()) {
                low = middle + 1;
            } else if (middleElement.hashCode() > element.hashCode()){
                high = middle - 1;
            } else {
                position = -1;
                break;
            }
        }
        return position;
    }

    /**
     * Get position of element in array.
     * Return -1 if there is no such element in array.
     * Else return its position.
     * @param element E.
     * @return int.
     */
    @SuppressWarnings("unchecked")
    private int findPositionOfElement(E element) {
        int position;
        int low = 0;
        int high = size - 1;
        while (true) {
            if (low > high) {
                position = -1;
                break;
            }
            int middle = (low + high) / 2;
            E middleElement = (E) elements[middle];
            if (middleElement.hashCode() < element.hashCode()) {
                low = middle + 1;
            } else if (middleElement.hashCode() > element.hashCode()){
                high = middle - 1;
            } else {
                position = middle;
                break;
            }
        }
        return position;
    }

    /**
     * Iterator.
     */
    private class ImprovedSortedSetIterator implements Iterator<E> {

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
            ImprovedSortedSet.this.remove(elements[prevIndex]);
            prevIndex = -1;
        }
    }
}
