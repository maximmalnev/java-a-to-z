package ru.kovladimir.webcrud;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.kovladimir.parser.db.ConfigLoader;

import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Connections pool.
 */
public class ConnectionPool {

    /**
     * Database's properties loader.
     */
    private ConfigLoader loader;

    /**
     * Available connections to use.
     */
    private Queue<Connection> availableConnections = new LinkedList<>();

    /**
     * Occupied connections by users.
     */
    private Set<Connection> occupiedConnections = new HashSet<>(20);

    /**
     * Default constructor.
     *
     * @param loader ConfigLoader.
     */
    public ConnectionPool(ConfigLoader loader) {
        this.loader = loader;
    }

    /**
     * Init connections.
     */
    public void init() {
        loader.loadConfig();
        for (int i = 0; i < 20; i++) {
            try {
                availableConnections.add(DriverManager.getConnection(
                        loader.getUrl(), loader.getUserName(), loader.getPassword()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get one available connection. Else - wait.
     *
     * @return Connection.
     */
    public Connection getAvailableConnection() {
        Connection connection;
        synchronized (this) {
            while (availableConnections.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            connection = availableConnections.poll();
            occupiedConnections.add(connection);
        }
        return connection;
    }

    /**
     * Release connection because user end his request.
     *
     * @param connection Connection.
     */
    public void releaseConnection(Connection connection) {
        synchronized (this) {
            occupiedConnections.remove(connection);
            availableConnections.add(connection);
            notifyAll();
        }
    }

    /**
     * Close all connections.
     */
    public void closeAllConnections() {
        // THIS IS WRONG
        try {
            // close all available connections
            for (Connection connection : availableConnections) {
                connection.close();
            }
            // close all occupied connections
            for (Connection connection : occupiedConnections) {
                connection.close();
            }
            // check if some of occupied connections become available and close them
            for (Connection connection : availableConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
