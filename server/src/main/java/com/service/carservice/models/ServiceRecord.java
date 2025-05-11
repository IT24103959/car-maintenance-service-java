package com.service.carservice.models;

import java.time.LocalDateTime;

public class ServiceRecord extends Record {
    private Car car;
    private LocalDateTime date;
    private String description;
    private int employeeId; // New attribute to link service records to employees

    public ServiceRecord() {
    }

    public ServiceRecord(LocalDateTime date, String description, double cost) {
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public ServiceRecord(int id, LocalDateTime date, String description, double cost) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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