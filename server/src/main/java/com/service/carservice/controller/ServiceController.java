package com.service.carservice.controller;

import com.service.carservice.models.ServiceRecord;
import com.service.carservice.models.Owner;
import com.service.carservice.models.Car;
import com.service.carservice.service.ServiceTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/service-records")
public class ServiceController {

    @Autowired
    private ServiceTrackerService serviceTrackerService;

    @PostMapping
    public ResponseEntity<ServiceRecord> createServiceRecord(@RequestBody Map<String, Object> payload) {
        try {
            // Extract owner information from payload
            Map<String, Object> ownerData = (Map<String, Object>) payload.get("owner");
            String name = (String) ownerData.get("name");
            String tel = (String) ownerData.get("tel");
            String email = (String) ownerData.get("email");
            String address = (String) ownerData.get("address");
            Owner owner = new Owner(name, tel,email, address);

            // Extract car information from payload
            Map<String, Object> carData = (Map<String, Object>) payload.get("car");
            String make = (String) carData.get("manufacturer");
            String carType = (String) carData.get("carType");
            String modelType = (String) carData.get("modelType");
            String registrationNumber = (String) carData.get("registrationNumber");
            String vin = (String) carData.get("vin");
            Car car = new Car(carType, make,modelType, vin, registrationNumber );

            // Set owner to car
            car.setOwner(owner);

            // Create service record
            Map<String, Object> serviceData = (Map<String, Object>) payload.get("service");

            LocalDateTime date = LocalDateTime.parse((String) serviceData.get("date"));
            String description = (String) serviceData.get("description");
            // Handle cost which might be an Integer or Double
            Object costObj = serviceData.get("cost");
            double cost;
            if (costObj instanceof Integer) {
                cost = ((Integer) costObj).doubleValue();
            } else {
                cost = (double) costObj;
            }

            ServiceRecord serviceRecord = new ServiceRecord(serviceTrackerService.getNextId(), date, description, cost);
            // Set car to service record
            serviceRecord.setCar(car);

            // Add the service record to the service
            serviceTrackerService.addServiceRecord(serviceRecord);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<List<ServiceRecord>> getServiceRecords() {
        List<ServiceRecord> serviceRecords = serviceTrackerService.getServiceRecords();
        return new ResponseEntity<>(serviceRecords, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRecord(@PathVariable int id) {
        serviceTrackerService.deleteServiceRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sorted")
    public List<ServiceRecord> getSortedServiceRecords(@RequestParam(defaultValue = "asc") String order) {
        serviceTrackerService.sortServiceRecordsByDate(order); // Sort the records
        return serviceTrackerService.getServiceRecords(); // Return the sorted list
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        try {
            // Extract service record data from payload
            Map<String, Object> serviceData = (Map<String, Object>) payload.get("service");
            LocalDateTime date = LocalDateTime.parse((String) serviceData.get("date"));
            String description = (String) serviceData.get("description");
            Object costObj = serviceData.get("cost");
            double cost;
            if (costObj instanceof Integer) {
                cost = ((Integer) costObj).doubleValue();
            } else {
                cost = (double) costObj;
            }

            // Extract car information from payload
            Map<String, Object> carData = (Map<String, Object>) payload.get("car");
            String make = (String) carData.get("manufacturer");
            String carType = (String) carData.get("carType");
            String modelType = (String) carData.get("modelType");
            String registrationNumber = (String) carData.get("registrationNumber");
            String vin = (String) carData.get("vin");
            Car car = new Car(carType, make, modelType, vin, registrationNumber);

            // Extract owner information from payload
            Map<String, Object> ownerData = (Map<String, Object>) payload.get("owner");
            String name = (String) ownerData.get("name");
            String tel = (String) ownerData.get("tel");
            String email = (String) ownerData.get("email");
            String address = (String) ownerData.get("address");
            Owner owner = new Owner(name, tel, email, address);

            // Set owner to car
            car.setOwner(owner);

            // Update the service record
            ServiceRecord updatedRecord = serviceTrackerService.updateServiceRecord(id, date, description, cost, car);

            if (updatedRecord == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceRecordById(@PathVariable int id) {
        ServiceRecord serviceRecord = serviceTrackerService.getServiceRecordById(id);
        if (serviceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(serviceRecord, HttpStatus.OK);
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<ServiceRecord> completeServiceRecord(@PathVariable int id) {
        ServiceRecord serviceRecord = serviceTrackerService.completeServiceRecord(id);
        if (serviceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(serviceRecord, HttpStatus.OK);
    }
}