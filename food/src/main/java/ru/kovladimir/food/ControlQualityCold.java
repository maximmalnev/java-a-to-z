package ru.kovladimir.food;

public class ControlQualityCold implements ControlCold{

    ControlReproduct control;

    private StorageCold[] places;

    /**
     * Default Constructor.
     *
     * @param places Storage[].
     */
    public ControlQualityCold(StorageCold[] places, ControlReproduct control) {
        this.places = places;
        this.control = control;
    }

    /**
     * Decide where put food.
     *
     * @param food Food.
     */
    public void add(FoodCold food) {
        boolean wasAdded = addToColdStorage(food);
        if (!wasAdded)
            control.add(food);
    }

    /**
     * Try to put food in StorageCold.
     * @param foodCold FoodCold.
     * @return true or false.
     */
    private boolean addToColdStorage(FoodCold foodCold) {
        boolean wasAdded = false;
        for (StorageCold storage : places) {
            if (storage.isAppropriate(foodCold)) {
                storage.addFood(foodCold);
                wasAdded = true;
            }
        }
        return wasAdded;
    }

    /**
     * {@inheritDoc}
     * @param food
     */
    public void add(FoodReproduct food) {
        control.add(food);
    }

    /**
     * {@inheritDoc}
     * @param food
     */
    public void add(Food food) {
        control.add(food);
    }
}
