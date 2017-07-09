package ru.kovladimir.carsale.persistence;

import java.util.List;

/**
 * DAO.
 * @param <E>
 */
public interface DAO<E> {

    /**
     * Get all elements.
     * @return List.
     */
    List<E> getAll();

    /**
     * Get eleemnt by id.
     * @param id int.
     * @return element.
     */
    E get(int id);

    /**
     * Add new element.
     * @param e element.
     */
    void create(E e);

    /**
     * Update element with same id.
     * @param e element.
     */
    void update(E e);

    /**
     * Delete element.
     * @param e element.
     */
    void delete(E e);

}
