package ru.kovladimir.iterators;


import java.util.Iterator;

/**
 * Converter.
 */
public interface Converter {

    /**
     * Convert Iterator of iterators to overall iterator.
     * @param it Iterator<Iterator<Integer>>. Iterator of Iterators.
     * @return Iterator<Integer>. Overall iterator.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);

}
