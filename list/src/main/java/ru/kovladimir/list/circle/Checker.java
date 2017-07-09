package ru.kovladimir.list.circle;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Checker.
 */
public class Checker {

    /**
     * First iterator.
     */
    Iterator first;

    /**
     * Second iterator.
     */
    Iterator second;

    /**
     * Constructor.
     * @param list SimpleLinkedList.
     */
    public Checker(SimpleLinkedList list) {
        first = list.iterator();
        second = list.iterator();
    }

    /**
     * Check if LinkedList is cyclic.
     * @return boolean.
     */
    public boolean isCyclic() {
        boolean isCyclic = false;
        Object firstObject;
        Object secondObject;
        try {
            do {
                firstObject = getNextFromFirstIterator();
                secondObject = getNextFromSecondIterator();
                if (firstObject == secondObject) {
                    isCyclic = true;
                }
            } while (!isCyclic || firstObject == null || secondObject == null);
        } catch (NoSuchElementException e) {
            isCyclic = false;
        }
        return isCyclic;
    }

    /**
     * Get next element from first iterator.
     * @return Object.
     */
    private Object getNextFromFirstIterator() {
        return first.next();
    }

    /**
     * Get next element from second iterator.
     * @return Object.
     */
    private Object getNextFromSecondIterator() {
        second.next();
        return second.next();
    }
}
