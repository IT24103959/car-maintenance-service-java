package com.service.carservice.services;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.DisposableBean;

public abstract class BaseService<T> implements DisposableBean {
    protected final LinkedList<T> items;

    protected BaseService(List<T> initialItems) {
        this.items = new LinkedList<>(initialItems);
    }

    public List<T> getAll() {
        return items;
    }

    public T getById(int id) {
        for (T item : items) {
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
