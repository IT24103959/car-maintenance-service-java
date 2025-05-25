package com.service.carservice.repositories;

import org.springframework.stereotype.Repository;

import com.service.carservice.models.Car;

@Repository
public class CarRepository extends BaseRepository<Car> {
    protected String getFilePath() {
        return "src/main/resources/data/cars.json";
    }

    protected Class<Car> getTypeClass() {
        return Car.class;
    }

    protected int getId(Car car) {
        return car.getId();
    }

}
