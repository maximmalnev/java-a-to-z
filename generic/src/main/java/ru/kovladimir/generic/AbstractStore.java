package ru.kovladimir.generic;

/**
 * Abstract store.
 * @param <T>
 */
public abstract class AbstractStore<T extends Base> implements Store<T>{

    /**
     * Array.
     */
    private SimpleArray<T> array = new SimpleArray<>();

    /**
     * {@inheritDoc}
     * @param value T.
     */
    @Override
    public void add(T value) {
        array.add(value);
    }

    /**
     * {@inheritDoc}
     * @param value T.
     */
    @Override
    public void update(T value) {
        int position = findPositionById(value.getId());
        if (position != -1) {
            array.update(position, value);
        }
    }

    /**
     * {@inheritDoc}
     * @param id String.
     */
    @Override
    public void delete(String id) {
        int position = findPositionById(id);
        if (position != -1) {
            array.delete(position);
        }
    }

    /**
     * {@inheritDoc}
     * @param id String.
     * @return
     */
    @Override
    public T get(String id) {
        return findBaseById(id);
    }

    /**
     * Get object from array by id.
     * @param id String.
     * @return T.
     */
    private T findBaseById(String id) {
        T result = null;
        for (int i = 0; i < array.getSize(); i++) {
            T value = array.get(i);
            if (value != null && value.getId().equals(id)) {
                result = value;
                break;
            }
        }
        return result;
    }

    /**
     * Get position of object with this id.
     * @param id String.
     * @return int.
     */
    private int findPositionById(String id) {
        int result = -1;
        for (int i = 0; i < array.getSize(); i++) {
            T value = array.get(i);
            if (value.getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

}
