package ru.kovladimir.carsale.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Session Factory.
 */
public class SessionHibernateFactory {

    private static SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    private SessionHibernateFactory() {

    }

    /**
     * Singleton.
     * @return factory.
     */
    public static SessionFactory getInstance() {
        return factory;
    }

    /**
     * Close factory.
     */
    public static void close() {
        factory.close();
    }

}
