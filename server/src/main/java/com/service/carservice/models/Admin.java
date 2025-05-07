package com.service.carservice.models;

public class Admin {
    private int id;
    private String name;

    public Admin(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getUsername() {
        return name;
    }

    public int getId() {
        return id;
    }

}
