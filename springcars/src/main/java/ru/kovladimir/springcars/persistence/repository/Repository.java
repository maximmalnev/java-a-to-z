package ru.kovladimir.springcars.persistence.repository;

import java.util.List;

/**
 * Repository.
 * @param <T>
 */
public interface Repository<T> {

    /**
     * Get all items.
     * @return
     */
    List<T> getAll();

    /**
     * Get item by id.
     * @param id
     * @return
     */
    T get(int id);

    /**
     * Create new item.
     * @param element
     */
    void create(T element);

    /**
     * Update item.
     * @param element
     */
    void update(T element);

    /**
     * Delete item.
     * @param element
     */
    void delete(T element);

}
