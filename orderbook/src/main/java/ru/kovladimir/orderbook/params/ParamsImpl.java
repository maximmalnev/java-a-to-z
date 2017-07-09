package ru.kovladimir.orderbook.params;

import java.io.File;

/**
 * Basic implementation.
 */
public class ParamsImpl implements Params {

    /**
     * Array.
     */
    private String params[];

    /**
     * Constructor.
     * @param params String[].
     */
    public ParamsImpl(String[] params) {
        this.params = params;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isValid() {
        return params.length == 2 && "-path".equals(params[0]);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public File getFile() throws NotValidArgsException {
        if (!isValid()) {
            throw new NotValidArgsException();
        } else {
            return new File(params[1]);
        }
    }
}
