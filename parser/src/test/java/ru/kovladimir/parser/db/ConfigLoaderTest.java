package ru.kovladimir.parser.db;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigLoaderTest {

    @Test
    public void whenLoadConfigReturnValidURL() {
        String url = "test url";
        String separator = File.separator;
        ConfigLoader loader = new ConfigLoader("src" + separator + "test" + separator +
                "resources" + separator + "testconfig.txt");

        loader.loadConfig();
        String result = loader.getUrl();

        assertThat(result, is(url));
    }

    @Test
    public void whenLoadConfigReturnValidName() {
        String name = "Test name";
        String separator = File.separator;
        ConfigLoader loader = new ConfigLoader("src" + separator + "test" + separator +
                "resources" + separator + "testconfig.txt");

        loader.loadConfig();
        String result = loader.getUserName();

        assertThat(result, is(name));
    }

    @Test
    public void whenLoadConfigReturnValidPassword() {
        String password = "Test password";
        String separator = File.separator;
        ConfigLoader loader = new ConfigLoader("src" + separator + "test" + separator +
                "resources" + separator + "testconfig.txt");

        loader.loadConfig();
        String result = loader.getPassword();

        assertThat(result, is(password));
    }

}