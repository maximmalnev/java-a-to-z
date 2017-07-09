package ru.kovladimir.list;

import java.util.*;

/**
 * Array container.
 * @param <E>
 */
public class ArrayContainer<E> implements SimpleContainer<E> {

    /**
     * Default size.
     */
    protected static final int DEFAULT_SIZE = 10;

    /**
     * Amount to extend array if there are no available places to add.
     */
    protected static final int AMOUNT_TO_EXTEND_ARRAY = 2;

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
    public ArrayContainer() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor with size.
     * @param size int.
     */
    public ArrayContainer(int size) {
        elements = new Object[size];
    }

    /**
     * {@inheritDoc}
     * @param element E.
     */
    @Override
    public void add(E element) {
        if (!havePlaceToAdd())
            extendArray();
        elements[size++] = element;
    }

    /**
     * {@inheritDoc}
     * @param index index.
     * @param element E.
     */
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (!havePlaceToAdd())
            extendArray();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * {@inheritDoc}
     * @param index int.
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * {@inheritDoc}
     * @param element Object.
     * @return
     */
    @Override
    public int indexOf(Object element) {
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
     * {@inheritDoc}
     * @param index int.
     * @param element E.
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * {@inheritDoc}
     * @param object Object.
     * @return
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * {@inheritDoc}
     * @param index
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(elements, index + 1, elements, index, elements.length - 1 - index);
        elements[--size] = null;
    }

    @Override
    public void remove(Object object) {
        int index = indexOf(object);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
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
    public Iterator<E> iterator() {
        return new ArrayContainerIterator();
    }

    /**
     * Check if there is at least one place to add element.
     * @return boolean.
     */
    private boolean havePlaceToAdd() {
        return elements.length > size;
    }

    /**
     * Extend array and save all elements in new array.
     */
    private void extendArray() {
        elements = Arrays.copyOf(elements, elements.length * AMOUNT_TO_EXTEND_ARRAY);
    }

    /**
     * Check if index is between 0 and size.
     * If not - throw IndexOutOfBoundsException.
     * @param index int.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Check if index is between 0 and size.
     * If not - throw IndexOutOfBoundsException.
     * @param index int.
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Iterator of ArrayContainer.
     */
    private class ArrayContainerIterator implements Iterator<E> {

        /**
         * Cursor of elements.
         */
        private int cursor = 0;

        /**
         * {@inheritDoc}
         * @return
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * {@inheritDoc}
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            return (E) elements[cursor++];
        }
    }
}
