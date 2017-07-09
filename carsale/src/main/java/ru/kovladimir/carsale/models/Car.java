package ru.kovladimir.carsale.models;

import java.util.Objects;

/**
 * Car.
 */
public class Car {

    private int id;

    private String name;

    private Transmission transmission;

    private Engine engine;

    private User user;

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

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(name, car.name) &&
                Objects.equals(transmission, car.transmission) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(user, car.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, transmission, engine, user);
    }
}
