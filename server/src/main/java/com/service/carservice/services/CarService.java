package com.service.carservice.services;

import com.service.carservice.models.Car;
import com.service.carservice.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService extends BaseService<Car> {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        super(carRepository.getAll());
        this.carRepository = carRepository;
    }

    public void addCar(Car car) {
        car.setId(carRepository.getNextId(true));
        items.add(car);
    }

    @Override
    protected int getId(Car car) {
        return car.getId();
    }

    public Car getCarById(int id) {
        return getById(id);
    }

    public boolean updateCarById(int id, Car updatedCar) {
        updatedCar.setId(id);
        return updateById(id, updatedCar);
    }

    public void persistOnShutdown() {
        carRepository.persistToFile(items);
    }
}
