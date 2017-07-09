package ru.kovladimir.food;

public class ControlQualityReproduct implements ControlReproduct{

    Control control;

    private StorageReproduct[] places;

    /**
     * Default Constructor.
     *
     * @param places Storage[].
     */
    public ControlQualityReproduct(StorageReproduct[] places, Control control) {
        this.places = places;
        this.control = control;
    }

    /**
     * Decide where put food.
     *
     * @param food Food.
     */
    public void add(FoodReproduct food) {
        boolean wasAdded = addToReproductStorage(food);
        if (!wasAdded)
            control.add(food);
    }

    /**
     * Try to put food in StorageReproduct.
     * @param foodReproduct FoodReproduct.
     * @return true or false.
     */
    private boolean addToReproductStorage(FoodReproduct foodReproduct) {
        boolean wasAdded = false;
        for (StorageReproduct storage : places) {
            if (storage.isAppropriate(foodReproduct)) {
                storage.addFood(foodReproduct);
                wasAdded = true;
            }
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     * @param food
     */
    public void add(Food food) {
        control.add(food);
    }
}
