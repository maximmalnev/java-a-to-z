package ru.kovladimir.set;

/**
 * Simple set.
 * @param <E>
 */
public interface SimpleSet<E> extends Iterable<E>  {

    /**
     * Add element to the set.
     * @param e E.
     * @return was added or not.
     */
    boolean add(E e);

    /**
     * Remove object from the set.
     * @param o Object.
     * @return was removed or not.
     */
    boolean remove(Object o);

    /**
     * Get size of the set.
     * @return int.
     */
    int size();

    /**
     * Check if set is empty.
     * @return boolean.
     */
    boolean isEmpty();

    /**
     * CHeck of the set contains object.
     * @param o Object.
     * @return boolean.
     */
    boolean contains(Object o);

}
