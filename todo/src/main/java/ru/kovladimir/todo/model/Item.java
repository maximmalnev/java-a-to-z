package ru.kovladimir.todo.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Item POJO.
 */
public class Item {

    private int id;

    private String description;

    private Timestamp createDate;

    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                done == item.done &&
                Objects.equals(description, item.description) &&
                Objects.equals(createDate, item.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, createDate, done);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", done=" + done +
                '}';
    }

}
