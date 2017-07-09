package ru.kovladimir.gc;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    public void createFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("test1");
            writer.println("test2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(String filename) {
        try {
            Files.delete(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCacheHasOneElementReturnItsValue() {
        Cache cache = new Cache();
        createFile("temp1.txt");

        String result = cache.getContent("temp1.txt");
        deleteFile("temp1.txt");


        assertThat(result, is("test1test2"));
    }

    @Test
    public void whenCacheHasTwoElementsThenFirstContentIsValid() {
        Cache cache = new Cache();
        createFile("temp1.txt");
        createFile("temp2.txt");

        String result = cache.getContent("temp1.txt");
        deleteFile("temp1.txt");
        deleteFile("temp2.txt");

        assertThat(result, is("test1test2"));
    }

    @Test
    public void whenCacheHasTwoElementsThenSecondContentIsValid() {
        Cache cache = new Cache();
        createFile("temp1.txt");
        createFile("temp2.txt");

        String result = cache.getContent("temp2.txt");
        deleteFile("temp1.txt");
        deleteFile("temp2.txt");

        assertThat(result, is("test1test2"));
    }
}