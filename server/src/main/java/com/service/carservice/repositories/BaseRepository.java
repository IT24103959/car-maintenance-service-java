package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseRepository<T> {
    protected int nextId = 1;
    protected final LinkedList<T> items = new LinkedList<>();
    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected abstract String getFilePath();

    protected abstract Class<T> getTypeClass();

    public BaseRepository() {
        loadFromFile();
    }

    protected void loadFromFile() {
        try {
            List<T> loaded = objectMapper.readValue(Files.readAllBytes(Paths.get(getFilePath())),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, getTypeClass()));
            items.clear();
            items.addAll(loaded);
            nextId = items.stream().mapToInt(this::getId).max().orElse(0) + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistToFile(List<T> items) {
        try {
            objectMapper.writeValue(Paths.get(getFilePath()).toFile(), items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        return items;
    }

    public int getNextId(boolean increment) {
        int id = nextId;
        if (increment)
            nextId++;
        return id;
    }

    protected abstract int getId(T item);
}
