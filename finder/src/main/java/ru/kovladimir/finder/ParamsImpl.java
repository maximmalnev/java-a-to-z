package ru.kovladimir.finder;

/**
 * Basic realization of Validator.
 */
public class ParamsImpl implements Params {

    /**
     * Params.
     */
    private String[] args;

    /**
     * Error message.
     */
    private String error = "There's no error.";

    public ParamsImpl(String[] args) {
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String getError() {
        return error;
    }

    @Override
    public String getDirectoryKey() {
        return args[1];
    }

    @Override
    public String getFileNameKey() {
        return args[3];
    }

    @Override
    public String getSearchKey() {
        return args[4];
    }

    @Override
    public String getPathToLogFile() {
        return args[6];
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isValid() {
        return checkLength() && checkKeyD() &&
                checkKeyN() && checkKeyMFR() && checkKeyO();
    }

    /**
     * Check length of the arguments. Should be 7.
     * @return boolean.
     */
    private boolean checkLength() {
        boolean valid = args != null && args.length == 7;
        if (!valid) {
            error = "The quantity of arguments are not 7.";
        }
        return valid;
    }

    /**
     * Check '-d' key.
     * @return boolean.
     */
    private boolean checkKeyD() {
        boolean valid = "-d".equals(args[0]);
        if (!valid) {
            error = "There is no key '-d'.";
        }
        return valid;
    }

    /**
     * Check '-n' key.
     * @return boolean.
     */
    private boolean checkKeyN() {
        boolean valid = "-n".equals(args[2]);
        if (!valid) {
            error = "There is no key '-n'.";
        }
        return valid;
    }

    /**
     * Check '-m', '-f' or '-r' key.
     * @return boolean.
     */
    private boolean checkKeyMFR() {
        boolean valid = "-m".equals(args[4]) || "-f".equals(args[4]) || "-r".equals(args[4]);
        if (!valid) {
            error = "There is no key '-m', '-f' or '-r'.";
        }
        return valid;
    }

    /**
     * Check '-o' key.
     * @return boolean.
     */
    private boolean checkKeyO() {
        boolean valid = "-o".equals(args[5]);
        if (!valid) {
            error = "There is no key '-o'.";
        }
        return valid;
    }

}
