package ru.kovladimir.list;

import java.util.*;

/**
 * Linked Container.
 *
 * @param <E>
 */
public class LinkedContainer<E> implements SimpleContainer<E> {

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
     *
     * @param element E.
     */
    @Override
    public void add(E element) {
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
    }

    /**
     * {@inheritDoc}
     *
     * @param index   index.
     * @param element E.
     */
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        Node<E> oldNode = getNodeByIndex(index);
        Node<E> newNode = new Node<>(oldNode.prev, element, oldNode);
        if (oldNode.next == null) {
            tail = oldNode;
        }
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        } else {
            head = newNode;
        }
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }

        size++;
    }

    /**
     * {@inheritDoc}
     *
     * @param index int.
     * @return
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).element;
    }

    /**
     * {@inheritDoc}
     *
     * @param element Object.
     * @return
     */
    @Override
    public int indexOf(Object element) {
        int index = -1;
        int currentPosition = -1;
        for (Node<E> node = head; node != null; node = node.next) {
            currentPosition++;
            if (Objects.equals(element, node.element)) {
                index = currentPosition;
                break;
            }
        }
        return index;
    }

    /**
     * {@inheritDoc}
     *
     * @param index   int.
     * @param element E.
     * @return
     */
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = getNodeByIndex(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    /**
     * {@inheritDoc}
     *
     * @param object Object.
     * @return
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * {@inheritDoc}
     *
     * @param index int.
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        Node<E> node = getNodeByIndex(index);
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
        /*if (next != null && prev == null) {
            next.prev = null;
            head = next;
        }*/
        size--;
    }

    /**
     * {@inheritDoc}
     *
     * @param object Object.
     */
    @Override
    public void remove(Object object) {
        remove(indexOf(object));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        for (Node<E> node = head; node != null; ) {
            Node<E> nextNode = node.next;
            node.prev = null;
            node.element = null;
            node.next = null;
            node = nextNode;
        }
        head = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedContainerForwardIterator();
    }

    /**
     * Get backward iterator.
     * @return Iterator<E>.
     */
    public Iterator<E> iteratorBackward() {
        return new LinkedContainerBackwardIterator();
    }

    /**
     * Get node by index.
     *
     * @param index int.
     * @return Node<E>.
     */
    private Node<E> getNodeByIndex(int index) {
        Node<E> node;
        if (index < size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    /**
     * Check if index is between 0 and size.
     * If not - throw IndexOutOfBoundsException.
     *
     * @param index int.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Check if index is between 0 and size.
     * If not - throw IndexOutOfBoundsException.
     *
     * @param index int.
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
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
    private class LinkedContainerForwardIterator implements Iterator<E> {

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
            LinkedContainer.this.remove(nextIndex - 1);
            lastReturned = null;
            nextIndex--;
        }
    }

    /**
     * Backward iterator.
     */
    private class LinkedContainerBackwardIterator implements Iterator<E> {

        /**
         * Next iterator.
         */
        private Node<E> next = tail;

        /**
         * Last returned node.
         */
        private Node<E> lastReturned;

        /**
         * Next index.
         */
        private int nextIndex = size - 1;

        /**
         * {@inheritDoc}
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return nextIndex >= 0;
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
            next = next.prev;
            nextIndex--;
            return lastReturned.element;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();
            LinkedContainer.this.remove(nextIndex + 1);
            lastReturned = null;
        }
    }
}
