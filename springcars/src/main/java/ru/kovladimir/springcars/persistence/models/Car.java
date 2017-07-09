package ru.kovladimir.springcars.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Car with info.
 */
public class Car {

    @JsonIgnore
    private int id;

    @NotNull
    private String producer;

    @NotNull
    private String model;

    @Min(value = 1900)
    private int year;

    @Min(value = 1)
    private int mileage;

    @Min(value = 1)
    private int engine;

    @Min(value = 1)
    private int price;

    @JsonIgnore
    private Ad ad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                mileage == car.mileage &&
                engine == car.engine &&
                price == car.price &&
                Objects.equals(producer, car.producer) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producer, model, mileage, engine, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", mileage=" + mileage +
                ", engine=" + engine +
                ", price=" + price +
                '}';
    }
}
