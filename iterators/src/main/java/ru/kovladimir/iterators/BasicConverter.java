package ru.kovladimir.iterators;

import java.util.*;

/**
 * Simple Converter.
 */
public class BasicConverter implements Converter {

    /**
     * {@inheritDoc}
     * @param it Iterator<Iterator<Integer>>. Iterator of Iterators.
     * @return
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            /**
             * Iterator of iterators.
             */
            private Iterator<Iterator<Integer>> outerIterator = it;

            /**
             * One of the inner iterators.
             */
            private Iterator<Integer> innerIterator = null;

            /**
             * {@inheritDoc}
             * @return
             */
            @Override
            public boolean hasNext() {
                boolean hasNextInnerIterator = false;
                if (innerIterator != null) {
                    hasNextInnerIterator = innerIterator.hasNext();
                }
                if ((innerIterator == null || !hasNextInnerIterator) && outerIterator.hasNext()) {
                    innerIterator = outerIterator.next();
                }
                return innerIterator.hasNext();
            }

            /**
             * {@inheritDoc}
             * @return
             */
            @Override
            public Integer next() {
                boolean hasNextInnerIterator = false;
                if (innerIterator != null) {
                    hasNextInnerIterator = innerIterator.hasNext();
                }
                if ((innerIterator == null || !hasNextInnerIterator) && outerIterator.hasNext()) {
                    innerIterator = outerIterator.next();
                }
                return innerIterator.next();
            }
        };
    }
}
