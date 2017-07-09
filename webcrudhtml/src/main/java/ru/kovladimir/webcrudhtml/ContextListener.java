package ru.kovladimir.webcrudhtml;

import ru.kovladimir.webcrud.PoolInitializer;
import ru.kovladimir.webcrudhtml.model.DBManager;
import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.view.HTMLBuilder;
import ru.kovladimir.webcrudhtml.view.SimpleHTMLBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Context listener.
 */
public class ContextListener implements ServletContextListener {

    /**
     * {@inheritDoc}
     *
     * @param sce
     */
    @Override

    public void contextInitialized(ServletContextEvent sce) {
        HTMLBuilder builder = new SimpleHTMLBuilder();
        DBManager manager = PostgreManager.getInstance();

        sce.getServletContext().setAttribute("builder", builder);
        sce.getServletContext().setAttribute("manager", manager);
    }

    /**
     * {@inheritDoc}
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PoolInitializer.close();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
