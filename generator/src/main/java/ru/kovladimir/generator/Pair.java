package ru.kovladimir.generator;

/**
 * Pair key-value.
 */
public class Pair {

    /**
     * Key.
     */
    private String key;

    /**
     * Value.
     */
    private String value;

    /**
     * Constructor.
     *
     * @param key   String.
     * @param value String.
     */
    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
