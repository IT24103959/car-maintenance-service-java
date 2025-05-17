package com.service.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.carservice.models.Car;
import com.service.carservice.services.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController extends BaseController<Car> {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<Car[]> getCars() {
        return response(carService.getAll(), Car.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        return response(carService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addCar(@RequestBody Car car) {
        carService.addCar(car);
        return response(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable int id) {
        carService.deleteById(id);
        return response(HttpStatus.NO_CONTENT);
    }

}
