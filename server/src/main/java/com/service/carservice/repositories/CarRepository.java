package com.service.carservice.repositories;

import com.service.carservice.cache.CarList;
import com.service.carservice.models.Car;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class CarRepository extends BaseRepository {
    private static final String FILE_PATH = "src/main/resources/data/cars.json";
    private final CarList items = new CarList();

    public CarRepository() {
        super();
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(getFilePath()))) {
                Car[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(getFilePath())),
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
                objectMapper.writeValue(Paths.get(getFilePath()).toFile(), data);
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