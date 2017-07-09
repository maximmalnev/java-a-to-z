package ru.kovladimir.list;

/**
 * Container.
 * @param <E>
 */
public interface SimpleContainer<E> extends Iterable<E> {

    /**
     * Add element to end.
     * @param element E.
     */
    void add(E element);

    /**
     * Add element in the position (index) and shift second part of array to the right.
     * @param index index.
     * @param element E.
     */
    void add(int index, E element);

    /**
     * Get element on index position.
     * @param index int.
     * @return E.
     */
    E get(int index);

    /**
     * Get position of element.
     * @param element Object.
     * @return int.
     */
    int indexOf(Object element);

    /**
     * Set element on index position and return old value.
     * @param index int.
     * @param element E.
     * @return E. Old value.
     */
    E set(int index, E element);

    /**
     * Check if container has object.
     * @param object Object.
     * @return boolean.
     */
    boolean contains(Object object);

    /**
     * Remove element by index and shift second part of array to the left.
     * @param index int.
     */
    void remove(int index);

    /**
     * Remove element and shift second part of array to the left.
     * @param object Object.
     */
    void remove(Object object);

    /**
     * Clear all elements in the container.
     */
    void clear();

    /**
     * Get quantity of elements in container.
     * @return int.
     */
    int size();

}
