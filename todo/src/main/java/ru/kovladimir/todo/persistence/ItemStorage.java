package ru.kovladimir.todo.persistence;

import org.hibernate.Session;
import ru.kovladimir.todo.model.Item;
import ru.kovladimir.todo.services.SessionHiberFactory;

import java.util.List;

/**
 * Item Storage.
 */
public class ItemStorage implements DAO<Item> {

    private final static ItemStorage storage = new ItemStorage();

    private ItemStorage() {

    }

    /**
     * SIngleton.
     * @return storage.
     */
    public static ItemStorage getInstance() {
        return storage;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Item> getAll() {
        List<Item> items;
        try (Session session = SessionHiberFactory.getInstance().openSession()) {
            session.beginTransaction();
            items = session.createQuery("from Item order by id").list();
            session.getTransaction().commit();
        }
        return items;
    }

    /**
     * {@inheritDoc}
     * @param id int.
     * @return
     */
    @Override
    public Item get(int id) {
        Item item;
        try (Session session = SessionHiberFactory.getInstance().openSession()) {
            session.beginTransaction();
            item = session.get(Item.class, id);
            session.getTransaction().commit();
        }
        return item;
    }

    /**
     * {@inheritDoc}
     * @param item
     */
    @Override
    public void create(Item item) {
        try (Session session = SessionHiberFactory.getInstance().openSession();
        ) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
    }

    /**
     * {@inheritDoc}
     * @param item
     */
    @Override
    public void update(Item item) {
        try (Session session = SessionHiberFactory.getInstance().openSession();
        ) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }
    }

    /**
     * {@inheritDoc}
     * @param item
     */
    @Override
    public void delete(Item item) {
        try (Session session = SessionHiberFactory.getInstance().openSession();
        ) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }
    }
}
