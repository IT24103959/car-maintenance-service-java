package com.service.carservice.cache;

import com.service.carservice.models.Admin;

class AdminLink {
    public Admin instance;
    public AdminLink next;

    public AdminLink(Admin instance) {
        this.instance = instance;
    }
}

public class AdminList extends BaseList {
    private AdminLink first;

    public AdminList() {
        super();
        first = null;
    }

    public void insert(Admin instance) {
        AdminLink newLink = new AdminLink(instance);

        if (first == null) {
            first = newLink;
        } else {
            AdminLink temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newLink;
        }
        size++;
    }

    public void deleteByValue(Admin instance) {
        AdminLink temp = first;
        AdminLink prev = null;
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

    public Admin[] toArray() {
        AdminLink temp = first;

        Admin[] data = new Admin[size];
        for (int i = 0; i < size; i++) {
            data[i] = temp.instance;
            temp = temp.next;
        }
        return data;
    }

    public Admin getByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        AdminLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.instance;
    }

    public void setByIndex(int index, Admin instance) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        AdminLink current = first;
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
