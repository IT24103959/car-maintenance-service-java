package com.service.carservice.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.util.LinkedList;

public abstract class BaseRepository<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseRepository.class);
    protected int nextId = 1;
    protected final LinkedList<T> items = new LinkedList<>();
    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected abstract String getFilePath();

    protected abstract Class<T> getTypeClass();

    public BaseRepository() {
        loadFromFile();
        setNextId();
    }

    protected void loadFromFile() {
        try {
            if (Files.exists(Paths.get(getFilePath()))) {
                T[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(getFilePath())),
                        objectMapper.getTypeFactory().constructArrayType(getTypeClass()));

                for (T record : data) {
                    items.add(record);
                }
            }
        } catch (IOException e) {
            logger.error("Error loading data from file", e);
        }
    }

    public void persistToFile(LinkedList<T> items) {
        if (items != null && items.size() > 0) {
            try {
                // Extract only the instance property from each Link and write as array
                T[] data = (T[]) java.lang.reflect.Array.newInstance(getTypeClass(), items.size());
                for (int i = 0; i < items.size(); i++) {
                    data[i] = items.get(i);
                }
                objectMapper.writeValue(Paths.get(getFilePath()).toFile(), data);
            } catch (IOException e) {
                logger.error("Error persisting data to file", e);
            }
        } else {
            try {
                // Write empty array if items is null or empty
                objectMapper.writeValue(Paths.get(getFilePath()).toFile(), new Object[0]);
            } catch (IOException e) {
                logger.error("Error persisting empty data to file", e);
            }
        }
    }

    public LinkedList<T> getAll() {
        return items;
    }

    public int getNextId(boolean increment) {
        int id = nextId;
        if (increment)
            nextId++;
        return id;
    }

    private void setNextId() {
        int maxId = 0;

        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);
            int id = getId(item);
            if (id > maxId) {
                maxId = id;
            }
        }

        nextId = maxId + 1;
    }

    protected abstract int getId(T item);
}
