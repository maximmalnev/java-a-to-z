package ru.kovladimir.parser;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParamsTest {

    @Test
    public void whenWrongLengthTheNotValid() {
        String[] args = new String[]{"1"};
        Params params = new Params(args);

        boolean valid = params.isValid();

        assertThat(valid, is(false));
    }

    @Test
    public void whenWrongLengthErrorAboutLength() {
        String[] args = new String[]{"1"};
        Params params = new Params(args);
        String error = "The quantity of arguments are not 3.";

        String result = params.getError();

        assertThat(result, is(error));
    }

    @Test
    public void whenWrongConfigFileThenNotValid() {
        String[] args = new String[]{"1", "1", "1"};
        Params params = new Params(args);

        boolean valid = params.isValid();

        assertThat(valid, is(false));
    }

    @Test
    public void whenWrongConfigFileThenErrorAboutFIle() {
        String[] args = new String[]{"1", "1", "1"};
        Params params = new Params(args);
        String error = "Can't find config file.";

        String result = params.getError();

        assertThat(result, is(error));
    }

    @Test
    public void whenWrongDirectoryThenNotValid() {
        String separator = File.separator;
        String validFile = "src" + separator + "test" + separator +
                "resources" + separator + "testconfig.txt";
        String[] args = new String[]{validFile, "1", "1"};
        Params params = new Params(args);

        boolean valid = params.isValid();

        assertThat(valid, is(false));
    }

    @Test
    public void whenWrongDirectoryThenErrorAboutDirectory() {
        String separator = File.separator;
        String validFile = "src" + separator + "test" + separator +
                "resources" + separator + "testconfig.txt";
        String[] args = new String[]{validFile, "1", "1"};
        Params params = new Params(args);
        String error = "Can't find directory for report.";

        String result = params.getError();

        assertThat(result, is(error));
    }

}