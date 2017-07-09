package ru.kovladimir.list;

/**
 * Simple queue.
 * @param <E>
 */
public interface SimpleQueue<E> {

    /**
     * Add element to queue.
     * @param element E.
     */
    void queue(E element);

    /**
     * Get last element.
     * @return E.
     */
    E dequeue();

    /**
     * Check if queue is empty.
     * @return boolean.
     */
    boolean isEmpty();
}
