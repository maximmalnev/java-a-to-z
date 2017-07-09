package ru.kovladimir.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ShopTest {

    @Test
    public void whenAddToShopThenContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 15);
        Shop place = new Shop(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        Food food = new Bread(name, creationDate, expirationDate, 25.0);

        place.addFood(food);
        Food result = place.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void whenAddToShopThenAddDiscount() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 29);
        Shop place = new Shop(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        Food food = new Bread(name, creationDate, expirationDate, 25.0);

        place.addFood(food);
        Food result = place.getStock()[0];
        double discount = result.getDiscount();

        assertEquals(discount, 20.0, 0.001);
    }

    @Test
    public void whenAddToShopNotValidFoodThenNotContainsIt() {
        Calendar today = Calendar.getInstance();
        today.set(2016, Calendar.JUNE, 2);
        Shop place = new Shop(today);
        String name = "Bread";
        Calendar creationDate = Calendar.getInstance();
        creationDate.set(2016, Calendar.JUNE, 1);
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2016, Calendar.JUNE, 31);
        Food food = new Bread(name, creationDate, expirationDate, 25.0);

        place.addFood(food);
        Food result = place.getStock()[0];

        assertNull(result);
    }

}