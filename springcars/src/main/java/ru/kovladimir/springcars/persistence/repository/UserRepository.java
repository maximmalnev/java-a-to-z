package ru.kovladimir.springcars.persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kovladimir.springcars.persistence.models.User;

import java.util.List;

/**
 * Repository to access users' info.
 */
@org.springframework.stereotype.Repository
public class UserRepository {

    private final SessionFactory factory;

    @Autowired
    public UserRepository(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Get user by its unique login.
     * @param login
     * @return
     */
    public User get(String login) {
        User user;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User " +
                    "where login = :login");
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
            session.getTransaction().commit();
        }
        return user;
    }
}
