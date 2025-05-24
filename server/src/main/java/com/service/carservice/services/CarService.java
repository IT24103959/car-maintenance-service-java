package com.service.carservice.services;

import com.service.carservice.cache.CarList;
import com.service.carservice.models.Car;
import com.service.carservice.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService extends BaseService {
    private final CarRepository carRepository;
    private CarList items;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.items = carRepository.getAll();
    }

    public void addCar(Car car) {
        car.setId(carRepository.getNextId(true));
        items.insert(car);
    }

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

    public boolean updateById(int id, Car updated) {
        for (int i = 0; i < items.getSize(); i++) {
            if (items.getByIndex(i).getId() == id) {
                items.setByIndex(i, updated);
                return true;
            }
        }
        return false;
    }

    public Car getById(int id){
        for (int i = 0; i < items.getSize(); i++) {
            Car item = items.getByIndex(i);
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        Car value = getById(id);
        items.deleteByValue(value);
    }


    public void persistOnShutdown() {
        carRepository.persistToFile(items);
    }

    public Car[] getAll() {
        return items.toArray();
    }
}