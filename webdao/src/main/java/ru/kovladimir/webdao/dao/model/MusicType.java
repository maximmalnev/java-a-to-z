package ru.kovladimir.webdao.dao.model;

import java.util.Objects;

/**
 * Created by Vladimir on 18.10.2016.
 */
public class MusicType {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicType musicType = (MusicType) o;
        return Objects.equals(name, musicType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
