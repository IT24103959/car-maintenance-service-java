package com.service.carservice.cache;

import com.service.carservice.models.Car;

class CarLink {
    public Car instance;
    public CarLink next;

    public CarLink(Car instance) {
        this.instance = instance;
    }
}

public class CarList extends BaseList {
    private CarLink first;

    public CarList() {
        super();
        first = null;
    }

    public void insert(Car instance) {
        CarLink newLink = new CarLink(instance);

        if (first == null) {
            first = newLink;
        } else {
            CarLink temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newLink;
        }
        size++;
    }

    public void deleteByValue(Car instance) {
        CarLink temp = first;
        CarLink prev = null;
        while (temp != null && temp.instance != instance) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return; // Value not found
        }
        if (prev == null) {
            first = temp.next; // Deleting the first element
            size--;
        } else {
            prev.next = temp.next; // Bypass the deleted
            size--;
        }
    }

    public Car[] toArray() {
        CarLink temp = first;

        Car[] data = new Car[size];
        for (int i = 0; i < size; i++) {
            data[i] = temp.instance;
            temp = temp.next;
        }
        return data;
    }

    public Car getByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        CarLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.instance;
    }

    public void setByIndex(int index, Car instance) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        CarLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.instance = instance;
    }

    public void clean() {
        first = null;
        size = 0;
    }

}
