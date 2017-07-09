package ru.kovladimir.set;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Linked set.
 * @param <E>
 */
public class LinkedSet<E> implements SimpleSet<E> {

    /**
     * First node.
     */
    private Node<E> head;

    /**
     * Last node.
     */
    private Node<E> tail;

    /**
     * Size.
     */
    private int size = 0;

    /**
     * {@inheritDoc}
     * @param element
     * @return
     */
    @Override
    public boolean add(E element) {
        boolean wasAdded = false;
        if (!contains(element)) {
            if (head == null && tail == null) {
                head = new Node<>(null, element, null);
                tail = head;
            } else {
                Node<E> oldNode = tail;
                Node<E> newNode = new Node<>(oldNode, element, null);
                oldNode.next = newNode;
                tail = newNode;
            }
            size++;
            wasAdded = true;
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     * @param o Object.
     * @return
     */
    @Override
    public boolean remove(Object o) {
        boolean wasRemoved = false;
        for (Node<E> node = head; node != null; node = node.next) {
            if (Objects.equals(o, node.element)) {
                Node<E> prev = node.prev;
                Node<E> next = node.next;
                if (prev != null) { // if node is not head
                    prev.next = next;
                } else { // if node is head
                    head = next;
                }
                if (next != null) { // if node is not tail
                    next.prev = prev;
                } else { // if node is tail
                    tail = prev;
                }
                wasRemoved = true;
                size--;
                break;
            }
        }
        return wasRemoved;
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
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     * @param o Object.
     * @return
     */
    @Override
    public boolean contains(Object o) {
        boolean has = false;
        for (Node<E> node = head; node != null; node = node.next) {
            if (Objects.equals(o, node.element)) {
                has = true;
                break;
            }
        }
        return has;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedSetIterator();
    }

    /**
     * Node that contains E element and links to previous and next node.
     *
     * @param <E>
     */
    private static final class Node<E> {

        /**
         * Previous node.
         */
        Node<E> prev;

        /**
         * Element.
         */
        E element;

        /**
         * Next node.
         */
        Node<E> next;

        /**
         * Constructor.
         *
         * @param prev    Node<E>.
         * @param element E.
         * @param next    Node<E>.
         */
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    /**
     * Forward iterator.
     */
    private class LinkedSetIterator implements Iterator<E> {

        /**
         * Next node.
         */
        private Node<E> next = head;

        /**
         * Last returned node.
         */
        private Node<E> lastReturned;

        /**
         * Next index.
         */
        private int nextIndex = 0;

        /**
         * {@inheritDoc}
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return nextIndex < size();
        }

        /**
         * {@inheritDoc}
         *
         * @return
         */
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            LinkedSet.this.remove(lastReturned.element);
            lastReturned = null;
            nextIndex--;
        }
    }

}
