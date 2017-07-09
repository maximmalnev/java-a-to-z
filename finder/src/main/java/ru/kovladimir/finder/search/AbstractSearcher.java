package ru.kovladimir.finder.search;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.util.List;

/**
 * Abstract searcher.
 */
public abstract class AbstractSearcher implements Searcher {

    /**
     * Search file in directory and subdirectories using Apache Commons IO.
     * @param directory File.
     * @param fileFilter IOFileFilter.
     * @param dirFilter IOFileFilter.
     * @return List<String> with file names.
     */
    public List<File> searchWithFileFilter(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
         return (List<File>) FileUtils.listFiles(directory, fileFilter, dirFilter);
    }
}
