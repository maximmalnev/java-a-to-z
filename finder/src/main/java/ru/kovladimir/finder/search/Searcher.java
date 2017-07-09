package ru.kovladimir.finder.search;

import java.io.File;
import java.util.List;

/**
 * Searcher for file in directory.
 */
public interface Searcher {

    /**
     * Search file in directory and subdirectories.
     * @param directory String.
     * @param fileName String.
     * @return List<String> with file names.
     */
    List<File> search(String directory, String fileName);

}
