package ru.kovladimir.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class WarehouseFreezerTest {

    @Test
    public void whenAddToWarehouseFreezerThenContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 3);
        WarehouseFreezer place = new WarehouseFreezer(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        FoodCold food = new AppleCold(name, creationDate, expirationDate, 25.0);
        food.setMustBeKeptInCold(true);

        place.addFood(food);
        FoodCold result = place.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void whenAddToWarehouseFreezerNotValidFoodThenNotContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 3);
        WarehouseFreezer place = new WarehouseFreezer(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        FoodCold food = new AppleCold(name, creationDate, expirationDate, 25.0);

        place.addFood(food);
        FoodCold result = place.getStock()[0];

        assertNull(result);
    }

}