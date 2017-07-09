package ru.kovladimir.carsale.persistence;

import ru.kovladimir.carsale.models.Car;
import ru.kovladimir.carsale.services.CarSearchParameters;

import java.util.List;

/**
 * Car repository.
 */
public interface CarRepository {

    /**
     * Get car by parameters.
     * @param parameters CarSearchParameters.
     * @return List.
     */
    List<Car> getByParameters(CarSearchParameters parameters);

}
