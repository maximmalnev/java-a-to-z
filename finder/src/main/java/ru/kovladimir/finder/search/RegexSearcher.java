package ru.kovladimir.finder.search;

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.List;

/**
 * File's searcher by regex.
 */
public class RegexSearcher extends AbstractSearcher {

    /**
     * {@inheritDoc}
     * @param directory String.
     * @param fileName String.
     * @return
     */
    @Override
    public List<File> search(String directory, String fileName) {
        return searchWithFileFilter(new File(directory), new RegexFileFilter(fileName), TrueFileFilter.INSTANCE);
    }
}
