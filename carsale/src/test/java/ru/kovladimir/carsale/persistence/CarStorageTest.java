package ru.kovladimir.carsale.persistence;

import org.junit.Test;
import ru.kovladimir.carsale.models.Car;
import ru.kovladimir.carsale.services.CarSearchParameters;

import static org.hamcrest.CoreMatchers.hasItem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import static org.junit.Assert.*;

public class CarStorageTest {

    private final CarStorage storage = CarStorage.getInstance();

    @Test
    public void whenAddCarThenStorageContainsIt() {
        Car car = getNewCar();

        storage.create(car);

        assertThat(storage.getAll(), hasItem(car));
    }

    @Test
    public void whenDeleteCarThenStorageDoesNotContainIt() {
        Car car = getNewCar();
        storage.create(car);

        storage.delete(car);

        assertThat(storage.getAll(), not(hasItem(car)));
    }

    @Test
    public void whenEditCarThenStorageHasNewVersion() {
        Car car = getNewCar();
        storage.create(car);

        car.setId(1);
        car.setName("newTest");
        storage.update(car);

        assertThat(storage.getAll().get(0).getName(), is("newTest"));
    }

    @Test
    public void whenSearchByParamsThenReturnRightValues() {
        Car car = getNewCar();
        storage.create(car);
        CarSearchParameters parameters = new CarSearchParameters();
        parameters.setCarName("test");

        assertThat(storage.getByParameters(parameters), hasItem(car));
    }

    private Car getNewCar() {
        Car car = new Car();
        car.setName("test");
        return car;
    }

}