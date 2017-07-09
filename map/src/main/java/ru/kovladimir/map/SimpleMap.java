package ru.kovladimir.map;

/**
 * Simple Map.
 * @param <K>
 * @param <V>
 */
public interface SimpleMap<K, V> {

    /**
     * Insert pair 'key-value' to the map.
     * @param key K.
     * @param value V.
     * @return boolean.
     */
    boolean insert(K key, V value);

    /**
     * Get value from map by key.
     * @param key K.
     * @return V.
     */
    V get(K key);

    /**
     * Delete pair 'key-value' from map by key.
     * @param key K.
     * @return boolean.
     */
    boolean delete(K key);

}
