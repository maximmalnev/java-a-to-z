package ru.kovladimir.list;

/**
 * Simple stack interface.
 *
 * @param <E>
 */
public interface SimpleStack<E> {

    /**
     * Push element to stack.
     *
     * @param element E.
     */
    void push(E element);

    /**
     * Get last added element and remove it.
     *
     * @return E.
     */
    E pop();

    /**
     * Get las added element without deletion.
     *
     * @return E.
     */
    E peek();

    /**
     * Check if stack is empty.
     * @return boolean.
     */
    boolean isEmpty();

}
