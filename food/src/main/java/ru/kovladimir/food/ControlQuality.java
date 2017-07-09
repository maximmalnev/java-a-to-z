package ru.kovladimir.food;

/**
 * Control Quality. Determine where put food.
 */
public class ControlQuality implements Control {

    private Storage[] places;

    /**
     * Default Constructor.
     * @param places Storage[].
     */
    public ControlQuality(Storage[] places) {
        this.places = places;
    }

    /**
     * Decide where put food.
     * @param food Food.
     */
    public void add(Food food) {
        for (Storage storage : places) {
            if (storage.isAppropriate(food)) {
                storage.addFood(food);
                break;
            }
        }
    }
}
