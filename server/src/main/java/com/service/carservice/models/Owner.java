package com.service.carservice.models;

public class Owner extends User {
    private String tel;
    private String address;

    public Owner(int id, String name, String email, String tel, String address) {
        super(id, name, email);
        this.tel = tel;
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
