package ru.kovladimir.generic;


/**
 * Array to keep objects.
 * @param <T>
 */
public class SimpleArray<T> {

    /**
     * Default size of array.
     */
    private static final int DEFAULT_SIZE = 5;

    /**
     * Main array.
     */
    private Object[] values;

    /**
     * Cursor of array.
     */
    private int cursor = 0;

    /**
     * Constructor without size.
     */
    public SimpleArray() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor with size.
     * @param size int.
     */
    public SimpleArray(int size) {
        values = new Object[size];
    }

    /**
     * Add value to array.
     * @param value T.
     */
    public void add(T value) {
        values[cursor++] = value;
        if (cursor >= values.length)
            extendArray();
    }

    /**
     * Replace old value to new value.
     * @param index int.
     * @param value T.
     */
    public void update(int index, T value) {
        if (index < values.length) {
            values[index] = value;
        }
    }

    /**
     * Delete object with this index.
     * @param index int.
     */
    public void delete(int index) {
        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        cursor--;
    }

    /**
     * Get object with this index.
     * @param index int.
     * @return T.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) values[index];
    }

    /**
     * Get size of array.
     * @return int.
     */
    public int getSize() {
        return values.length;
    }

    /**
     * Extend array if there are no empty places.
     */
    private void extendArray() {
        Object[] extendedArray = new Object[values.length * 2];
        System.arraycopy(values, 0, extendedArray, 0, values.length);
        values = extendedArray;
    }


}
