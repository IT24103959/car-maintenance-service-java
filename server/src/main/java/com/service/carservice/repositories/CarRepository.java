package com.service.carservice.repositories;

import org.springframework.stereotype.Repository;

import com.service.carservice.models.Car;

@Repository
public class CarRepository extends BaseRepository<Car> {
    @Override
    protected String getFilePath() {
        return "src/main/resources/data/cars.json";
    }

    @Override
    protected Class<Car> getTypeClass() {
        return Car.class;
    }

    @Override
    protected int getId(Car car) {
        return car.getId();
    }

}
