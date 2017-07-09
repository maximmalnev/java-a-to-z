package ru.kovladimir.orderbook.params;

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
     * Get XML File.
     * @return File.
     */
    File getFile() throws NotValidArgsException;

}
