package ru.kovladimir.threads.find;

import java.io.File;

/**
 * Params.
 */
public interface Params {

    /**
     * Check if params are valid.
     * @return boolean.
     */
    boolean isValid();

    /**
     * Get start directory to search.
     * @return File.
     */
    File getStartDirectory();

    /**
     * Get file name to search.
     * @return String.
     */
    String getFileName();

}
