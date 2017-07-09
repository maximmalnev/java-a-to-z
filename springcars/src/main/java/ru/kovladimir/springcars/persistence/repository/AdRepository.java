package ru.kovladimir.springcars.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kovladimir.springcars.persistence.models.Ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository to access ad from database.
 */
@org.springframework.stereotype.Repository
public class AdRepository implements Repository<Ad> {

    private final SessionFactory factory;

    @Autowired
    public AdRepository(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Ad> getAll() {
        List<Ad> adList = new ArrayList<>();
        try (Session session = factory.openSession()){
            session.beginTransaction();
            adList.addAll(session.createQuery("from Ad order by id desc ").list());
            session.getTransaction().commit();
        }
        return adList;
    }

    /**
     * {@inheritDoc}
     * @param id
     * @return
     */
    @Override
    public Ad get(int id) {
        Ad ad;
        try (Session session = factory.openSession()){
            session.beginTransaction();
            ad = session.get(Ad.class, id);
            session.getTransaction().commit();
        }
        return ad;
    }

    /**
     * {@inheritDoc}
     * @param element
     */
    @Override
    public void create(Ad element) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.save(element);
            session.getTransaction().commit();
        }
    }

    /**
     * {@inheritDoc}
     * @param element
     */
    @Override
    public void update(Ad element) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.update(element);
            session.getTransaction().commit();
        }
    }

    /**
     * {@inheritDoc}
     * @param element
     */
    @Override
    public void delete(Ad element) {
        try (Session session = factory.openSession()){
            session.beginTransaction();
            session.delete(element);
            session.getTransaction().commit();
        }
    }
}
