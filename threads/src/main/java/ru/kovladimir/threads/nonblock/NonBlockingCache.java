package ru.kovladimir.threads.nonblock;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Cache.
 */
public class NonBlockingCache<T extends Base> {

    /**
     * Map.
     */
    private ConcurrentHashMap<Integer, T> map = new ConcurrentHashMap<>();

    /**
     * Add to map.
     *
     * @param base Base.
     */
    public void add(T base) {
        map.putIfAbsent(base.getId(), base);
    }

    /**
     * Update value with same ID.
     * @param newValue T.
     * @throws OptimisticException
     */
    public void update(T newValue) throws OptimisticException {
        map.computeIfPresent(newValue.getId(), new BiFunction<Integer, T, T>() {
            @Override
            public T apply(Integer integer, T t) {
                if (t.getVersion() == newValue.getVersion()) {
                    newValue.incrementVersion();
                    return newValue;
                } else {
                    throw new OptimisticException();
                }
            }
        });
    }


    /**
     * Delete Base from map by id.
     *
     * @param id int.
     */
    public void delete(int id) {
        map.remove(id);
    }

}
