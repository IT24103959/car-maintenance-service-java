package com.service.carservice.models;

public class Car extends Vehicle {
    private String carType;

    public Car() {
    }

    public Car(String carType, String manufacturer, String modelType, String vin, String registrationNumber) {
        super(manufacturer, modelType, vin, registrationNumber);
        this.carType = carType;
    }

    public Car(String carType, String manufacturer, String modelType) {
        super(manufacturer, modelType);
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
