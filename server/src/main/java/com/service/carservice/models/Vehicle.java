package com.service.carservice.models;

public abstract class Vehicle {
    protected int id;
    protected String manufacturer;
    protected String modelType;
    protected String vin;
    protected String registrationNumber;
    protected Owner owner;

    public Vehicle() {
    }

    public Vehicle(int id, String manufacturer, String modelType, String vin, String registrationNumber) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.modelType = modelType;
        this.vin = vin;
        this.registrationNumber = registrationNumber;
    }

    public Vehicle(String manufacturer, String modelType, String vin, String registrationNumber) {
        this.manufacturer = manufacturer;
        this.modelType = modelType;
        this.vin = vin;
        this.registrationNumber = registrationNumber;
    }

    public Vehicle(String manufacturer, String modelType) {
        this.manufacturer = manufacturer;
        this.modelType = modelType;
    }

    public abstract String getCarType();

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModelType() {
        return modelType;
    }

    public String getVin() {
        return vin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}