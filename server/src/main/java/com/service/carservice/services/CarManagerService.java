package com.service.carservice.services;

import com.service.carservice.models.Car;
import com.service.carservice.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarManagerService {

    private final CarRepository carRepository;
    private LinkedList<Car> cars;

    @Autowired
    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.cars = new LinkedList<>(carRepository.getAllCars());
    }

    public void addCar(Car car) {
        car.setId(carRepository.getNextId()); // Fetch nextId from repository
        cars.add(car);
        carRepository.addCar(car);
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public void deleteCar(int id) {
        cars.removeIf(car -> car.getId() == id);
        carRepository.persistToFile(cars);
    }
}
