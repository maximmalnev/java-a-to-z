package ru.kovladimir.threads.find;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParamsImplTest {


    public void createFile(String fileName)  {
        try {
            Files.createFile(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String fileName)  {
        try {
            Files.delete(Paths.get(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenArgsValidReturnTrue()  {
        String fileName = "111.txt";
        createFile(fileName);
        Params params = new ParamsImpl(new String[]{System.getProperty("user.dir"), fileName});

        boolean valid = params.isValid();
        deleteFile(fileName);

        assertThat(valid, is(true));
    }

    @Test
    public void whenAmountOfArgsNotValidReturnFalse()  {
        String fileName = "111.txt";
        createFile(fileName);
        Params params = new ParamsImpl(new String[]{fileName});

        boolean valid = params.isValid();
        deleteFile(fileName);

        assertThat(valid, is(false));
    }

    @Test
    public void whenDirectoryNotExistThenReturnFalse()  {
        String fileName = "111.txt";
        createFile(fileName);
        Params params = new ParamsImpl(new String[]{fileName, fileName});

        boolean valid = params.isValid();
        deleteFile(fileName);

        assertThat(valid, is(false));
    }

    @Test (expected = RuntimeException.class)
    public void whenNotValidAndGetDirectoryThenThrowException()  {
        String fileName = "111.txt";
        createFile(fileName);
        Params params = new ParamsImpl(new String[0]);

        try {
            params.getStartDirectory();
        } finally {
            deleteFile(fileName);
        }
    }

    @Test (expected = RuntimeException.class)
    public void whenNotValidAndGetFileNameThenThrowException()  {
        String fileName = "111.txt";
        createFile(fileName);
        Params params = new ParamsImpl(new String[0]);

        try {
            params.getFileName();
        } finally {
            deleteFile(fileName);
        }
    }
}