package ru.kovladimir.threads.nonblock;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Base.
 */
public class Base {

    /**
     * ID.
     */
    private final int id;

    /**
     * Version.
     */
    private int version = 0;


    /**
     * Data.
     */
    private final String data;


    /**
     * Constructor.
     * @param id int.
     * @param data String.
     */
    public Base(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }int getVersion() {
        return version;
    }

    public void incrementVersion() {
        version++;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                Objects.equals(data, base.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                ", data='" + data + '\'' +
                '}';
    }
}
