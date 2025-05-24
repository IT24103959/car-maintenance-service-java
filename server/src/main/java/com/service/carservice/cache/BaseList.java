package com.service.carservice.cache;

public class BaseList {
    protected int size;

    public BaseList() {
        this.size = 0;
    }

    public int getSize(){
        return  size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
