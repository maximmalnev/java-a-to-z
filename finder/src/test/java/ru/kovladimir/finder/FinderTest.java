package ru.kovladimir.finder;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FinderTest {

    private void createStructureOfFiles() throws IOException {
        Files.createDirectories(Paths.get(String.format("%s%s%s", "Temp", File.separator, "test1")));
        Files.createFile(Paths.get(String.format("%s%s%s", "Temp", File.separator,"asd.txt")));
        Files.createFile(Paths.get(String.format("%s%s%s", "Temp", File.separator,"asdf.txt")));
        Files.createFile(Paths.get(String.format("%1$s%2$s%3$s%2$s%4$s", "Temp", File.separator, "test1", "asd.txt")));
    }

    private void deleteStructureOfFiles() throws IOException {
        FileUtils.deleteDirectory(new File("Temp"));
    }

    private List<String> getAllNamesFromLog() throws IOException {
        List<String> names = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream("Temp" + File.separator + "log.txt"));
        while (scanner.hasNextLine()) {
            names.add(scanner.nextLine());
        }
        scanner.close();
        return names;
    }

    @Test
    public void whenFindWithWildCardThenLogFileShouldContainAbsolutePaths() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        List<String> names = Arrays.asList(new File(String.format("%s%s%s", "Temp", File.separator,"asdf.txt")).getAbsolutePath());
        Params params = new ParamsImpl(new String[]{"-d", "Temp", "-n", "????.txt", "-m", "-o", log});
        Finder finder = new Finder(params);

        finder.start();
        List<String> foundNames = getAllNamesFromLog();
        deleteStructureOfFiles();

        assertEquals(names, foundNames);
    }

    @Test
    public void whenFindWithWildCardThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", "????.txt", "-m", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("Search has finished. The result is in the log file." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithExactNameThenLogFileShouldContainAbsolutePaths() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        List<String> names = Arrays.asList(new File(String.format("%s%s%s", "Temp", File.separator,"asdf.txt")).getAbsolutePath());
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", "asdf.txt", "-f", "-o", log});
        Finder finder = new Finder(params);

        finder.start();
        List<String> foundNames = getAllNamesFromLog();
        deleteStructureOfFiles();

        assertEquals(names, foundNames);
    }

    @Test
    public void whenFindWithExactNameThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", "asdf.txt", "-f", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("Search has finished. The result is in the log file." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithRegexThenLogFileShouldContainAbsolutePaths() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        List<String> names = Arrays.asList(new File(String.format("%s%s%s", "Temp", File.separator,"asdf.txt")).getAbsolutePath());
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", ".{4}\\.txt", "-r", "-o", log});
        Finder finder = new Finder(params);

        finder.start();
        List<String> foundNames = getAllNamesFromLog();
        deleteStructureOfFiles();

        assertEquals(names, foundNames);
    }

    @Test
    public void whenFindWithRegexThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", ".{4}\\.txt", "-r", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("Search has finished. The result is in the log file." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithoutValidLengthThenPrintError() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"Temp", "-n", "????.txt", "-m", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("The quantity of arguments are not 7." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithoutDArgThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"fail", "Temp", "-n", "????.txt", "-m", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("There is no key '-d'." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithoutNArgThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "fail", "????.txt", "-m", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("There is no key '-n'." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithoutMFRArgThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", "????.txt", "fail", "-o", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("There is no key '-m', '-f' or '-r'." + System.lineSeparator(), outputStream.toString());
    }

    @Test
    public void whenFindWithoutOArgThenPrintMessage() throws IOException {
        createStructureOfFiles();
        String log = "Temp" + File.separator + "log.txt";
        Params params = new ParamsImpl(new String[] {"-d", "Temp", "-n", "????.txt", "-m", "fail", log});
        Finder finder = new Finder(params);
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        finder.start();
        deleteStructureOfFiles();
        System.setOut(oldOut);

        assertEquals("There is no key '-o'." + System.lineSeparator(), outputStream.toString());
    }

}