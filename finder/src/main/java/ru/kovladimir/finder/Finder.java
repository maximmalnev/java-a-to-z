package ru.kovladimir.finder;

import ru.kovladimir.finder.io.*;
import ru.kovladimir.finder.search.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Finder. Main Class.
 */
public class Finder {

    /**
     * Output to print error.
     */
    private Output outputForUser = new ConsoleOutput();

    /**
     * Validator to check arguments.
     */
    private Params params;

    /**
     * Result. List of files' names.
     */
    private List<File> foundFiles = new ArrayList<>();

    /**
     * Searcher.
     */
    private Searcher searcher;

    /**
     * Constructor.
     * @param params Params arguments.
     */
    public Finder(Params params) {
        this.params = params;
    }

    /**
     * Start. Main loop.
     */
    public void start() {
        if (!params.isValid()) {
            printError();
        } else {
            initSearcherByArg();
            findAllFiles();
            logResult();
            printSuccess();
        }
    }

    /**
     * Print error to user.
     */
    private void printError() {
        outputForUser.println(params.getError());
    }

    /**
     * Init searcher by argument.
     * If '-m' - WildcardSearcher.
     * If '-f' - ExactNameSearcher.
     * If '-r' - RegexSearcher.
     */
    private void initSearcherByArg() {
        String keySearcher = params.getSearchKey();
        if ("-m".equals(keySearcher)) {
            searcher = new WildcardSearcher();
        } else if ("-f".equals(keySearcher)) {
            searcher = new ExactNameSearcher();
        } else if ("-r".equals(keySearcher)) {
            searcher = new RegexSearcher();
        } else {
            throw new RuntimeException("Unknown search key.");
        }
    }

    /**
     * Start finding files in directory.
     */
    private void findAllFiles() {
        foundFiles = searcher.search(params.getDirectoryKey(), params.getFileNameKey());
    }

    /**
     * Print result to log-file.
     */
    private void logResult() {
        File logFile = new File(params.getPathToLogFile());
        try (PrintStream printStream = new PrintStream(new FileOutputStream(logFile))) {
            for (File file : foundFiles) {
                printStream.println(file.getAbsolutePath());
            }
        } catch (FileNotFoundException e) {
            outputForUser.println("Can not create log file.");
        }
    }

    /**
     * Print success message.
     */
    private void printSuccess() {
        outputForUser.println("Search has finished. The result is in the log file.");
    }

    public static void main(String[] args) {
        Finder finder = new Finder(new ParamsImpl(args));
        finder.start();
    }
}
