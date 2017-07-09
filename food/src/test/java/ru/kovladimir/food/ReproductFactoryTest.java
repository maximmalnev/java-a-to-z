package ru.kovladimir.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ReproductFactoryTest {

    @Test
    public void whenAddToTrashThenContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JULY, 2);
        ReproductFactory place = new ReproductFactory(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        FoodReproduct food = new AppleReproduct(name, creationDate, expirationDate, 25.0);
        food.setCanBeReproducted(true);

        place.addFood(food);
        Food result = place.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void whenAddToTrashNotValidFoodThenNotContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JULY, 2);
        ReproductFactory place = new ReproductFactory(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        FoodReproduct food = new AppleReproduct(name, creationDate, expirationDate, 25.0);

        place.addFood(food);
        Food result = place.getStock()[0];

        assertNull(result);
    }

}