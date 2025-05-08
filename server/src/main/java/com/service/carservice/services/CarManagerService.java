package com.service.carservice.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.service.carservice.models.Car;

@Service
public class CarManagerService {

    private static final String FILE_PATH = "src/main/resources/data/cars.txt";
    private LinkedList<Car> cars;

    public CarManagerService() {
        cars = new LinkedList<>();
        loadCars();
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
        saveCars();
    }

    private void loadCars() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String carType = parts[0];
                String manufacturer = parts[1];
                String modelType = parts[2];
                Car car = new Car(carType, manufacturer, modelType);
                cars.add(car);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCars() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Car car : cars) {
                bw.write(car.getCarType() + "," + car.getManufacturer() + "," + car.getModelType());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}