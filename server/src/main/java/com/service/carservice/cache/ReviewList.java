package com.service.carservice.cache;

import com.service.carservice.models.Review;

class ReviewLink {
    public Review instance;
    public ReviewLink next;

    public ReviewLink(Review instance) {
        this.instance = instance;
    }
}

public class ReviewList extends BaseList {
    private ReviewLink first;

    public ReviewList() {
        super();
        first = null;
    }

    public void insert(Review instance) {
        ReviewLink newLink = new ReviewLink(instance);

        if (first == null) {
            first = newLink;
        } else {
            ReviewLink temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newLink;
        }
        size++;
    }

    public void deleteByValue(Review instance) {
        ReviewLink temp = first;
        ReviewLink prev = null;
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

    public Review[] toArray() {
        ReviewLink temp = first;

        Review[] data = new Review[size];
        for (int i = 0; i < size; i++) {
            data[i] = temp.instance;
            temp = temp.next;
        }
        return data;
    }

    public Review getByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ReviewLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.instance;
    }

    public void setByIndex(int index, Review instance) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ReviewLink current = first;
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
