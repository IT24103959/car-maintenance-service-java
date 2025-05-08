package com.service.carservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.service.carservice.services.CarManagerService;
import com.service.carservice.models.Car;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cars")
public class CarController {
    
    @Autowired
    private CarManagerService carManagerService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carManagerService.getCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> addCar(@RequestBody Car car) {
        carManagerService.addCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
      
    }
    
    

}
