package com.service.carservice.util;

class Link<T> {
    public T instance;
    public Link<T> next;

    public Link(T instance) {
        this.instance = instance;
    }
}

public class LinkedList<T> {
    private Link<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(T value) {
        Link<T> newLink = new Link<>(value);
        if (head == null) {
            head = newLink;
        } else {
            Link<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newLink;
        }
        size++;
    }

    public boolean remove(T value) {
        if (head == null)
            return false;
        if (head.instance.equals(value)) {
            head = head.next;
            size--;
            return true;
        }
        Link<T> current = head;
        while (current.next != null) {
            if (current.next.instance.equals(value)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Link<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.instance;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Link<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.instance = value;
    }

    public T[] modelDumpJson(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }

}
