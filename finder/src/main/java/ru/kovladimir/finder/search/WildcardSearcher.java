package ru.kovladimir.finder.search;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.File;
import java.util.List;

/**
 * File's searcher by wildcard.
 */
public class WildcardSearcher extends AbstractSearcher {

    /**
     * {@inheritDoc}
     * @param directory String.
     * @param fileName String.
     * @return
     */
    @Override
    public List<File> search(String directory, String fileName) {
        return searchWithFileFilter(new File(directory), new WildcardFileFilter(fileName), TrueFileFilter.INSTANCE);
    }
}
