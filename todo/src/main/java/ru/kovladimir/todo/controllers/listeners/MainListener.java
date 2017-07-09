package ru.kovladimir.todo.controllers.listeners;

import ru.kovladimir.todo.services.SessionHiberFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Main Listener to init and close hibernate sessions
 */
public class MainListener implements ServletContextListener {

    /**
     * Init hibernate factory.
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionHiberFactory.getInstance();
    }

    /**
     * Close hibernate factory.
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionHiberFactory.close();
    }
}
