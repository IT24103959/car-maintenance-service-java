package com.service.carservice.models;

public abstract class Record {
    protected int id;
    protected double cost;
    protected boolean completed = false;

    abstract void setCost(double cost);
    abstract void setCompleted(boolean completed);

    public int getId() {
        return id;
    }
    public double getCost(){
        return cost;
    }
    public boolean getCompleted() {
        return completed;
    }
}