package ru.kovladimir.finder.search;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RegexSearcherTest {

    private void createStructureOfFiles() throws IOException {
        Files.createDirectories(Paths.get(String.format("%s%s%s", "Temp", File.separator, "test1")));
        Files.createFile(Paths.get(String.format("%s%s%s", "Temp", File.separator,"asd.txt")));
        Files.createFile(Paths.get(String.format("%s%s%s", "Temp", File.separator,"asdf.txt")));
        Files.createFile(Paths.get(String.format("%1$s%2$s%3$s%2$s%4$s", "Temp", File.separator, "test1", "asd.txt")));
    }

    private void deleteStructureOfFiles() throws IOException {
        FileUtils.deleteDirectory(new File("Temp"));
    }

    @Test
    public void whenSearchByExactNameThenReturnListOfAbsolutePaths() throws IOException {
        createStructureOfFiles();
        List<File> names = Arrays.asList(new File("Temp" + File.separator + "asd.txt"),
                new File("Temp" + File.separator + "test1" + File.separator + "asd.txt"));

        Searcher searcher = new RegexSearcher();
        List<File> foundFiles = searcher.search("Temp", ".{3}.txt");
        deleteStructureOfFiles();

        assertEquals(names, foundFiles);
    }

}