package ru.kovladimir.threads.find;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileFinderTest {

    public void createStructureOfFiles() {
        try {
            String separator = File.separator;
            Files.createDirectory(Paths.get("dir0"));
            Files.createDirectory(Paths.get(String.format("dir0%sdir1", separator)));
            Files.createDirectory(Paths.get(String.format("dir0%sdir2", separator)));
            Files.createDirectory(Paths.get(String.format("dir0%sdir3", separator)));
            Files.createDirectory(Paths.get(String.format("dir0%1$sdir1%1$sdir11", separator)));
            Files.createFile(Paths.get(String.format("dir0%1$sdir1%1$sdir11%1$stext1.txt", separator)));
            Files.createFile(Paths.get(String.format("dir0%1$sdir3%stext2.txt", separator)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteStructureOfFiles() {
        try {
            FileUtils.deleteDirectory(new File("dir0"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFileExistThenPrintSuccess() {
        createStructureOfFiles();
        String separator = File.separator;
        FileFinder finder = new FileFinder(new ParamsImpl(new String[]{"dir0", "text2.txt"}));
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        finder.start();
        System.setOut(oldOut);
        String filePath = Paths.get(String.format("dir0%1$sdir3%stext2.txt", separator)).toFile().getAbsolutePath();
        deleteStructureOfFiles();

        assertThat(outContent.toString(), is(String.format("File is found!%n%s", filePath)));
    }

    @Test
    public void whenFileDoesNotExistThenPrintFail() {
        createStructureOfFiles();
        String separator = File.separator;
        FileFinder finder = new FileFinder(new ParamsImpl(new String[]{"dir0", "text3.txt"}));
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        finder.start();
        System.setOut(oldOut);
        deleteStructureOfFiles();

        assertThat(outContent.toString(), is(String.format("File was not found.%n")));
    }

}