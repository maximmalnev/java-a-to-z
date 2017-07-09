package ru.kovladimir.threads.lists;

import ru.kovladimir.list.SimpleContainer;

import java.util.Iterator;

/**
 * Synchronized Abstract Container.
 * @param <E>
 */
public abstract class SynchronizedSimpleContainer<E> implements SimpleContainer<E> {

    /**
     * Array Container.
     */
    private SimpleContainer<E> container;

    /**
     * Constructor.
     * @param container SimpleContainer.
     */
    public SynchronizedSimpleContainer(SimpleContainer<E> container) {
        this.container = container;
    }

    @Override
    public synchronized void add(E element) {
        container.add(element);
    }

    /**
     * {@inheritDoc}
     * @param index index.
     * @param element E.
     */
    @Override
    public synchronized void add(int index, E element) {
        container.add(index, element);
    }

    /**
     * {@inheritDoc}
     * @param index int.
     * @return
     */
    @Override
    public synchronized E get(int index) {
        return container.get(index);
    }

    /**
     * {@inheritDoc}
     * @param element Object.
     * @return
     */
    @Override
    public synchronized int indexOf(Object element) {
        return container.indexOf(element);
    }

    /**
     * {@inheritDoc}
     * @param index int.
     * @param element E.
     * @return
     */
    @Override
    public synchronized E set(int index, E element) {
        return container.set(index, element);
    }

    /**
     * {@inheritDoc}
     * @param object Object.
     * @return
     */
    @Override
    public synchronized boolean contains(Object object) {
        return container.contains(object);
    }

    /**
     * {@inheritDoc}
     * @param index int.
     */
    @Override
    public synchronized void remove(int index) {
        container.remove(index);
    }

    /**
     * {@inheritDoc}
     * @param object Object.
     */
    @Override
    public synchronized void remove(Object object) {
        container.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void clear() {
        container.clear();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public synchronized int size() {
        return container.size();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public synchronized Iterator<E> iterator() {
        return container.iterator();
    }
}
