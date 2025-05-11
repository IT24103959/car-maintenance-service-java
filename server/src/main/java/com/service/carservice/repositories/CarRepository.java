package com.service.carservice.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.models.Car;

@Repository
public class CarRepository {

    private static final String JSON_FILE_PATH = "src/main/resources/data/cars.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private int nextId;

    public CarRepository() {
        List<Car> cars = loadCars(); // Directly load cars from JSON
        this.nextId = cars.stream()
                .mapToInt(Car::getId)
                .max()
                .orElse(0) + 1;
    }

    private List<Car> loadCars() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(JSON_FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Car.class));
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void addCar(Car car) {
        car.setId(nextId++); // Use nextId and increment
        List<Car> cars = loadCars();
        cars.add(car);
        persistToFile(cars);
    }

    public List<Car> getAllCars() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(JSON_FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Car.class));
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void persistToFile(List<Car> cars) {
        try {
            objectMapper.writeValue(Paths.get(JSON_FILE_PATH).toFile(), cars);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        return nextId;
    }
}
