package ru.kovladimir.carsale.services;

import java.util.Optional;

/**
 * Params.
 */
public class CarSearchParameters {

    private String carName;

    private String engineName;

    private String transmissionName;

    private String ownerLogin;

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public void setTransmissionName(String transmissionName) {
        this.transmissionName = transmissionName;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public Optional<String> getCarName() {
        return Optional.ofNullable(carName);
    }

    public Optional<String> getEngineName() {
        return Optional.ofNullable(engineName);
    }

    public Optional<String> getTransmissionName() {
        return Optional.ofNullable(transmissionName);
    }

    public Optional<String> getOwnerLogin() {
        return Optional.ofNullable(ownerLogin);
    }
}
