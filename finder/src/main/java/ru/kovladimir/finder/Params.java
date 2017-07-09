package ru.kovladimir.finder;

/**
 * Arguments' validator.
 */
public interface Params {

    /**
     * Arguments valid or not.
     * @return boolean.
     */
    boolean isValid();

    /**
     * Get error message.
     * @return String.
     */
    String getError();

    String getDirectoryKey();

    String getFileNameKey();

    String getSearchKey();

    String getPathToLogFile();
}
