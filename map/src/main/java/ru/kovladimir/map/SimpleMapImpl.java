package ru.kovladimir.map;

import java.util.*;

/**
 * Simple map.
 * @param <K>
 * @param <V>
 */
public class SimpleMapImpl<K, V> implements SimpleMap<K, V>, Iterable<SimpleMapImpl.Entry> {

    /**
     * Amount to extend array if there is no place to add.
     */
    protected static int AMOUNT_TO_EXTEND = 2;

    /**
     * Default size of array.
     */
    protected static int DEFAULT_SIZE = 4;

    /**
     * Array of entries.
     */
    private Entry<K, V>[] entries;

    /**
     * Constructor without params.
     */
    public SimpleMapImpl() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor with size.
     * @param size int.
     */
    @SuppressWarnings("unchecked")
    public SimpleMapImpl(int size) {
        entries = (Entry<K, V>[]) new Entry[size];
    }

    /**
     * {@inheritDoc}
     * @param key K.
     * @param value V.
     * @return
     */
    @Override
    public boolean insert(K key, V value) {
        boolean wasAdded = false;
        int position = getPosition(key);
        Entry<K, V> oldEntry = entries[position];
        if (oldEntry == null) {
            entries[position] = new Entry<>(key, value);
            wasAdded = true;
        } else if (Objects.equals(key, oldEntry.getKey())) {
            oldEntry.value = value;
        } else {
            extendArray();
            refillEntries();
            insert(key, value);
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     * @param key K.
     * @return
     */
    @Override
    public V get(K key) {
        V value = null;
        Entry<K, V> entry = entries[getPosition(key)];
        if (entry != null) {
            value = entry.getValue();
        }
        return value;
    }

    /**
     * {@inheritDoc}
     * @param key K.
     * @return
     */
    @Override
    public boolean delete(K key) {
        boolean wasDeleted = false;
        int position = getPosition(key);
        Entry<K, V> entry = entries[position];
        if (entry != null && Objects.equals(key, entry.getKey())) {
            entries[position] = null;
            wasDeleted = true;
        }
        return wasDeleted;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Iterator<Entry> iterator() {
        return new SimpleMapImplIterator();
    }

    /**
     * Get bucket to entry.
     * @param key K.
     * @return int.
     */
    private int getPosition(K key) {
        int position = 0;
        if (key != null) {
            position = key.hashCode() % entries.length;
        }
        return position;
    }

    /**
     * Extend array.
     */
    private void extendArray() {
        entries = Arrays.copyOf(entries, entries.length * AMOUNT_TO_EXTEND);
    }

    /**
     * Refill entries.
     */
    @SuppressWarnings("unchecked")
    private void refillEntries() {
        Entry<K, V>[] oldEntries = entries;
        entries = (Entry<K, V>[]) new Entry[entries.length];
        for (Entry<K, V> entry : oldEntries) {
            if (entry != null) {
                entries[getPosition(entry.getKey())] = entry;
            }
        }
    }

    /**
     * Entry. Pair 'key-value'.
     * @param <K>
     * @param <V>
     */
    public static class Entry<K, V> {

        /**
         * Key.
         */
        private K key;

        /**
         * Value.
         */
        private V value;

        /**
         * Default constructor.
         * @param key K.
         * @param value V.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Iterator.
     */
    private class SimpleMapImplIterator implements Iterator<Entry> {

        /**
         * Next index.
         */
        private int nextIndex = 0;

        /**
         * Previous index.
         */
        private int prevIndex = -1;

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public boolean hasNext() {
            boolean has = false;
            int position = nextIndex;
            while (!has && position < entries.length) {
                if (entries[position] != null) {
                    has = true;
                } else {
                    position++;
                }
            }
            return has;
        }

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public Entry<K, V> next() {
            Entry<K, V> nextEntry = null;
            for (int i = 0; i < entries.length; i++) {
                Entry<K, V> entry = entries[i];
                if (entry != null) {
                    nextEntry = entry;
                    prevIndex = nextIndex;
                    nextIndex = i + 1;
                    break;
                }
            }
            if (nextEntry == null) {
                throw new NoSuchElementException();
            }
            return nextEntry;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (prevIndex == -1) {
                throw new IllegalStateException();
            } else {
                delete(entries[prevIndex].getKey());
                prevIndex = -1;
            }
        }
    }
}