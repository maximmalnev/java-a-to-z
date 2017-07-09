package ru.kovladimir.parser.db;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Config loader to get properties to database connection.
 */
public class ConfigLoader {

    /**
     * Path to file with config.
     */
    private String pathToConfig;

    /**
     * Flag. If it's true then there's no need to load properties again.
     */
    private boolean configWsLoaded = false;

    /**
     * Properties from file.
     */
    private Properties properties = new Properties();

    /**
     * Consctuctor with path to config file.
     * @param pathToConfig String.
     */
    public ConfigLoader(String pathToConfig) {
        this.pathToConfig = pathToConfig;
    }

    /**
     * Load config from file if it's wasn't loaded before.
     */
    public void loadConfig() {
        if (!configWsLoaded) {
            try {
                properties.load(new FileReader(pathToConfig));
                configWsLoaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get url to the database.
     * @return String.
     */
    public String getUrl() {
        return properties.getProperty("url");
    }

    /**
     * Get user name to connect to the database.
     * @return String.
     */
    public String getUserName() {
        return properties.getProperty("username");
    }

    /**
     * Get password to connect to the database.
     * @return String.
     */
    public String getPassword() {
        return properties.getProperty("password");
    }

    /**
     * Get database driver.
     * @return String.
     */
    public String getDriver() {
        return properties.getProperty("driver");
    }

}
