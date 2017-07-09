package ru.kovladimir.generic;

/**
 * Store to keep classes.
 * @param <T>
 */
public interface Store<T extends Base> {

    /**
     * Add object to store.
     * @param value T.
     */
    void add(T value);

    /**
     * Change if in object.
     * @param value T.
     */
    void update(T value);

    /**
     * Delete object from store.
     * @param id String.
     */
    void delete(String id);

    /**
     * Get object by ID.
     * @param id String.
     * @return T.
     */
    T get(String id);
}
