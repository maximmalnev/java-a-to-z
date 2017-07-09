package ru.kovladimir.generator;

/**
 * Generator.
 */
public interface Generator {

    /**
     * Return string where all params were replaced by data.
     * @param template String.
     * @param data Object[].
     * @return String.
     */
    String generate(String template, Pair[] data) throws ParamNotFoundException;

}
