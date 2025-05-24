package com.service.carservice.models;

public class ServiceRecord extends BaseModel {
    private Car car;
    private String date;
    private String description;
    private int employeeId;
    private double cost;
    private boolean completed = false;


    public double getCost() {
        return cost;
    }

    public boolean getCompleted() {
        return completed;
    }

    public ServiceRecord() {
    }

    public ServiceRecord(String date, String description, double cost) {
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public ServiceRecord(int id, String date, String description, double cost, int employeeId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.employeeId = employeeId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}