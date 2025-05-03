package com.service.carservice.models;

public class Owner {
    private String name;
    private String tel;
    private String email;
    private String address;

    public Owner(String name, String tel, String email, String address) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
}
