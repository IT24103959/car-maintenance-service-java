package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.cache.CarList;
import com.service.carservice.models.Car;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class CarRepository {
    private static final String FILE_PATH = "src/main/resources/data/cars.json";
    private final CarList items = new CarList();
    private int nextId;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public CarRepository() {
        loadFromFile();
        setNextId();
    }

    public int getNextId(boolean increment){
        int id = nextId;
        if(increment){
            nextId++;
        }
        return id;
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                Car[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(FILE_PATH)),
                        objectMapper.getTypeFactory().constructArrayType(Car.class));

                for (Car record : data) {
                    items.insert(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistToFile(CarList items) {
        if (items != null && items.getSize() > 0) {
            try {
                Car[] data = items.toArray();
                objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNextId() {
        int maxId = 0;
        Car[] data = items.toArray();
        for (Car record : data) {
            int id = record.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        nextId = maxId + 1;
    }

    public CarList getAll(){
        return items;
    }

}