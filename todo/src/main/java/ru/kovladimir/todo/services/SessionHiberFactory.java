package ru.kovladimir.todo.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Vladimir on 21.10.2016.
 */
public class SessionHiberFactory {

    private static SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    private SessionHiberFactory() {

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
