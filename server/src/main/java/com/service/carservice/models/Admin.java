package com.service.carservice.models;

public class Admin {
    private int id;
    private String name;
    private String email;

    public Admin(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
