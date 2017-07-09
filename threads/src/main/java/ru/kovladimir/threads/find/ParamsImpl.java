package ru.kovladimir.threads.find;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 * Basic implementation pf params.
 */
public class ParamsImpl implements Params{

    /**
     * Args.
     */
    private String[] args;

    /**
     * Constructor.
     * @param args String[].
     */
    public ParamsImpl(String[] args) {
        this.args = args;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isValid() {
        return args.length == 2 && Files.isDirectory(Paths.get(args[0])) &&
                args[1] != null && args[1].length() > 0;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public File getStartDirectory() {
        if (!isValid()) {
            throw new RuntimeException("Directory does not exist.");
        } else {
            return new File(args[0]);
        }
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String getFileName() {
        if (!isValid()) {
            throw new RuntimeException("Wrong file name.");
        } else {
            return args[1];
        }
    }
}
