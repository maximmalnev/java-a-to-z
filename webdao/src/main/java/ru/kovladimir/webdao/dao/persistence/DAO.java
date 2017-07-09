package ru.kovladimir.webdao.dao.persistence;

import java.util.List;

/**
 * Created by Vladimir on 18.10.2016.
 */
public interface DAO<E> {

    List<E> getAll();

    E get(long id);

    void add(E element);

    void update(E element);

    void delete(E element);

}
