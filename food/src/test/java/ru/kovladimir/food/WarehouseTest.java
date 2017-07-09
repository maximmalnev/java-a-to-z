package ru.kovladimir.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class WarehouseTest {

    @Test
    public void whenAddToWarehouseThenContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 3);
        Warehouse warehouse = new Warehouse(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        Food food = new Bread(name, creationDate, expirationDate, 25.0);

        warehouse.addFood(food);
        Food result = warehouse.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void whenAddToWarehouseNotValidFoodThenNotContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 15);
        Warehouse warehouse = new Warehouse(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        Food food = new Bread(name, creationDate, expirationDate, 25.0);

        warehouse.addFood(food);
        Food result = warehouse.getStock()[0];

        assertNull(result);
    }
}