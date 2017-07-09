package ru.kovladimir.food;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ControlQualityColdTest {

    private Calendar creationDate = Calendar.getInstance();
    private Calendar expirationDate = Calendar.getInstance();
    private Calendar today = Calendar.getInstance();
    private Warehouse warehouse;
    private Shop shop;
    private Trash trash;


    private ReproductFactory reproductFactory;
    private ControlQualityReproduct controlQualityReproduct;

    private WarehouseFreezer warehouseFreezer;
    private ControlQualityCold controlQualityCold;


    private void init(Calendar today) {
        creationDate.set(2016, Calendar.JUNE, 1);
        expirationDate.set(2016, Calendar.JUNE, 31);
        warehouse = new Warehouse(today);
        shop = new Shop(today);
        trash = new Trash(today);
        Storage[] places = new Storage[]{warehouse, shop, trash};
        ControlQuality controlQuality = new ControlQuality(places);

        reproductFactory = new ReproductFactory(today);
        StorageReproduct[] placesReproduct = new StorageReproduct[]{reproductFactory};
        controlQualityReproduct = new ControlQualityReproduct(placesReproduct, controlQuality);

        warehouseFreezer = new WarehouseFreezer(today);
        StorageCold[] placesCold = new StorageCold[]{warehouseFreezer};
        controlQualityCold = new ControlQualityCold(placesCold, controlQualityReproduct);
    }

    @Test
    public void testPutToWarehouse() {
        today.set(2016, Calendar.JUNE, 3);
        init(today);
        Food food = new Butter("Butter", creationDate, expirationDate, 25.0);

        controlQualityCold.add(food);
        Food result = warehouse.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void testPutToShop() {
        today.set(2016, Calendar.JUNE, 10);
        init(today);
        Food food = new Butter("Butter", creationDate, expirationDate, 25.0);

        controlQualityCold.add(food);
        Food result = shop.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void testPutToShopWithDiscount() {
        today.set(2016, Calendar.JUNE, 28);
        init(today);
        Food food = new Butter("Butter", creationDate, expirationDate, 25.0);

        controlQualityCold.add(food);
        Food result = shop.getStock()[0];
        double discount = result.getDiscount();

        assertEquals(food, result);
        assertEquals(discount, 20.0, 0.001);
    }

    @Test
    public void testPutToTrash() {
        today.set(2016, Calendar.JULY, 3);
        init(today);
        Food food = new Butter("Butter", creationDate, expirationDate, 25.0);

        controlQualityCold.add(food);
        Food result = trash.getStock()[0];

        assertEquals(food, result);
    }



    @Test
    public void testPutToReproductFactory() {
        today.set(2016, Calendar.JULY, 3);
        init(today);
        FoodReproduct food = new AppleReproduct("Butter", creationDate, expirationDate, 25.0);
        food.setCanBeReproducted(true);

        controlQualityCold.add(food);
        FoodReproduct result = reproductFactory.getStock()[0];

        assertEquals(food, result);
    }

    @Test
    public void testPutToWarehouseFreezer() {
        today.set(2016, Calendar.JUNE, 3);
        init(today);
        FoodCold food = new AppleCold("Butter", creationDate, expirationDate, 25.0);
        food.setMustBeKeptInCold(true);

        controlQualityCold.add(food);
        FoodCold result = warehouseFreezer.getStock()[0];

        assertEquals(food, result);
    }

}