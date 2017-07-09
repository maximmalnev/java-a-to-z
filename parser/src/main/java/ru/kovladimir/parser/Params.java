package ru.kovladimir.parser;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Params.
 */
public class Params {

    /**
     * Args from input.
     */
    private String[] args;

    /**
     * Error message.
     */
    private String error = "There's no error.";

    private boolean wasChecked = false;

    private boolean valid = false;

    /**
     * Constructor.
     *
     * @param args String[].
     */
    public Params(String[] args) {
        this.args = args;
    }

    /**
     * Check if all args are valid.
     *
     * @return valid or not.
     */
    public boolean isValid() {
        if (!wasChecked) {
            valid = checkLength() && checkConfigFile() && checkDirectory();
            wasChecked = true;
        }
        return valid;
    }

    /**
     * Get error message.
     *
     * @return String.
     */
    public String getError() {
        if (!wasChecked) {
            isValid();
        }
        return error;
    }

    /**
     * Get path to config file for database connection.
     *
     * @return String.
     */
    public String getPathToConfig() {
        return args[0];
    }

    /**
     * Get directory to create report.
     *
     * @return String/
     */
    public String getDirectoryForReport() {
        return args[1];
    }

    /**
     * Get frequency to update vacancies in minutes.
     *
     * @return minutes.
     */
    public int getPeriod() {
        return Integer.parseInt(args[2]);
    }

    /**
     * Check args' length.
     *
     * @return boolean.
     */
    private boolean checkLength() {
        boolean valid = args != null && args.length == 3;
        if (!valid) {
            error = "The quantity of arguments are not 3.";
        }
        return valid;
    }

    /**
     * Check that config file exists.
     *
     * @return boolean.
     */
    private boolean checkConfigFile() {
        boolean valid = Files.exists(Paths.get(args[0]));
        if (!valid) {
            error = "Can't find config file.";
        }
        return valid;
    }

    /**
     * Check if report's directory exists.
     *
     * @return boolean.
     */
    private boolean checkDirectory() {
        boolean valid = Files.exists(Paths.get(args[1]));
        if (!valid) {
            error = "Can't find directory for report.";
        }
        return valid;
    }

}
