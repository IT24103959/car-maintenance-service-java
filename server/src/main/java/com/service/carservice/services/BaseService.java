package com.service.carservice.services;

import org.springframework.beans.factory.DisposableBean;

import com.service.carservice.util.LinkedList;

public abstract class BaseService<T> implements DisposableBean {
    protected final LinkedList<T> items;

    protected BaseService(LinkedList<T> initialItems) {
        this.items = initialItems;
    }

    public LinkedList<T> getAll() {
        return items;
    }

    public T getById(int id) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            if (getId(item) == id) {
                return item;
            }
        }
        return null;
    }

    public boolean updateById(int id, T updated) {
        for (int i = 0; i < items.size(); i++) {
            if (getId(items.get(i)) == id) {
                items.set(i, updated);
                return true;
            }
        }
        return false;
    }

    public void deleteById(int id) {
        items.removeIf(item -> getId(item) == id);
    }

    protected abstract int getId(T item);

    @Override
    public void destroy() {
        persistOnShutdown();
    }

    protected abstract void persistOnShutdown();
}
