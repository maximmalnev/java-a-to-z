package ru.kovladimir.set;

import java.util.*;

/**
 * Set with buckets.
 * @param <E>
 */
public class ImprovedSet<E> implements SimpleSet<E> {

    /**
     * Amount of buckets.
     */
    protected static final int NUMBER_OF_BUCKETS = 10;

    /**
     * Buckets.
     */
    private List<SimpleSet<E>> sets;

    /**
     * Constructor.
     */
    public ImprovedSet() {
        sets = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_BUCKETS; i++) {
            sets.add(new ArraySet<>());
        }
    }

    /**
     * {@inheritDoc}
     * @param e E.
     * @return
     */
    @Override
    public boolean add(E e) {
        return sets.get(getBucket(e)).add(e);
    }

    /**
     * {@inheritDoc}
     * @param o Object.
     * @return
     */
    @Override
    public boolean remove(Object o) {
        return sets.get(getBucket(o)).remove(o);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public int size() {
        int size = 0;
        for (SimpleSet<E> set : sets) {
            size += set.size();
        }
        return size;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     * @param o Object.
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return sets.get(getBucket(o)).contains(o);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new ImprovedSetIterator();
    }

    /**
     * Get bucket by hashcode.
     * @param o Object.
     * @return int.
     */
    private int getBucket(Object o) {
        return o.hashCode() % NUMBER_OF_BUCKETS;
    }

    /**
     * Iterator of Improved Set.
     */
    private class ImprovedSetIterator implements Iterator<E> {

        /**
         * Index of current bucket.
         */
        private int bucketIndex = 0;

        /**
         * Current iterator.
         */
        private Iterator<E> currentIterator = null;

        /**
         * Previous iterator.
         */
        private Iterator<E> previousIterator = null;

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public boolean hasNext() {
            if (currentIterator == null) {
                currentIterator = sets.get(0).iterator();
            }
            boolean has;
            do {
                has = currentIterator.hasNext();
                if (!has) {
                    changeIterator();
                }
            } while (!has && bucketIndex < NUMBER_OF_BUCKETS - 1);
            return has;
        }

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public E next() {
            if (currentIterator == null) {
                currentIterator = sets.get(0).iterator();
            }
            E element = null;

            do {
                if (currentIterator.hasNext()) {
                    element = currentIterator.next();
                } else {
                    changeIterator();
                }
            } while (element == null && bucketIndex < NUMBER_OF_BUCKETS - 1);

            if (element == null) {
                throw new NoSuchElementException();
            }
            previousIterator = currentIterator;
            return element;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (previousIterator == null) {
                throw new IllegalStateException();
            }
            previousIterator.remove();
        }

        /**
         * Change current iterator to the next iterator.
         */
        private void changeIterator() {
            if (bucketIndex < NUMBER_OF_BUCKETS - 1) {
                currentIterator = sets.get(++bucketIndex).iterator();
            }
        }
    }
}
