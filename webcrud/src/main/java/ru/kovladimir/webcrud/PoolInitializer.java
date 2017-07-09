package ru.kovladimir.webcrud;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.kovladimir.parser.db.ConfigLoader;

import java.beans.PropertyVetoException;
import java.sql.*;

/**
 * Pool Initializer.
 */
public class PoolInitializer {

    /**
     * Pool.
     */
    private static final ComboPooledDataSource pool;

    static {
        String configPath = System.getenv("CRUD_CONFIG");
        if (configPath == null) {
            throw new RuntimeException("Wrong path to config");
        }
        ConfigLoader loader = new ConfigLoader(configPath);
        loader.loadConfig();
        pool = new ComboPooledDataSource();
        try {
            pool.setDriverClass(loader.getDriver());
            pool.setJdbcUrl(loader.getUrl());
            pool.setUser(loader.getUserName());
            pool.setPassword(loader.getPassword());
            pool.setMinPoolSize(3);
            pool.setMaxPoolSize(30);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get connection from pool.
     * @return Connection.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
        pool.close();
    }

}
