package com.service.carservice.service;

import com.service.carservice.models.Car;
import com.service.carservice.models.Owner;
import com.service.carservice.models.ServiceRecord;
import com.service.carservice.util.SelectionSort;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;


@Service
public class ServiceTrackerService {
    private static final String FILE_PATH = "src/main/resources/data/service-history.txt";
    private LinkedList<ServiceRecord> serviceRecords;
    private int nextId = 1; // Initialize nextId to 1

    public ServiceTrackerService() {
        serviceRecords = new LinkedList<>();
        loadServiceRecords();
        setNextId(); // Set nextId based on loaded records
    }

    public void addServiceRecord(ServiceRecord record) {
        serviceRecords.add(record);
        saveServiceRecords();
        nextId++; // Increment nextId after adding a new record
    }

    public ServiceRecord updateServiceRecord(int id, LocalDateTime date, String description, double cost, Car car) {
        ServiceRecord record = getServiceRecordById(id); // Assume this method exists to fetch a record by ID
        if (record != null) {
            record.setDate(date);
            record.setDescription(description);
            record.setCost(cost);
            record.setCar(car);
        }
        return record;
    }

    public ServiceRecord completeServiceRecord(int id){
        ServiceRecord record = getServiceRecordById(id); // Assume this method exists to fetch a record by ID
        if (record != null) {
            record.setCompleted(true);
        }
        return record;
    }

    public ServiceRecord getServiceRecordById(int id) {
        return serviceRecords.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public int getNextId() {
        return nextId;
    }

    private void setNextId() {
        int maxId = 0;
        for (ServiceRecord record : serviceRecords) {
            if (record.getId() > maxId) {
                maxId = record.getId();
            }
        }
        nextId = maxId + 1; // Set nextId to the maximum ID + 1
    }

    public void deleteServiceRecord(int id) {
        serviceRecords.removeIf(record -> record.getId() == id);
        saveServiceRecords();
        nextId--; // Decrement nextId after deleting a record
    }

    public void sortServiceRecordsByDate(String order) {
        if(order.equalsIgnoreCase("asc")){
            SelectionSort.sortAscending(serviceRecords);
        }else{
            SelectionSort.sortDescending(serviceRecords);
        }
    }

    private void loadServiceRecords() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length > 0) {
                    LocalDateTime date = LocalDateTime.parse(parts[1]); // Default ISO-8601 format (yyyy-MM-dd)
                    Owner owner = new Owner(parts[9], parts[10], parts[11], parts[12]);
                    Car car = new Car(parts[4], parts[5], parts[6], parts[7], parts[8]);
                    car.setOwner(owner);

                    // Create a new ServiceRecord object
                    ServiceRecord record = new ServiceRecord(Integer.parseInt(parts[0]), date, parts[2], Double.parseDouble(parts[3]));
                    record.setCar(car);
                    record.setCompleted(Boolean.parseBoolean(parts[13])); // Assuming the last part is a boolean for completion status
                    serviceRecords.add(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveServiceRecords() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ServiceRecord record : serviceRecords) {
                Car car = record.getCar();
                Owner owner = car.getOwner();
                bw.write(
                        record.getId()
                                + "," +
                                record.getDate()
                                + "," +
                                record.getDescription()
                                + "," +
                                record.getCost()
                                + "," +
                                car.getCarType()
                                + "," +
                                car.getManufacturer()
                                + "," +
                                car.getModelType()
                                + "," +
                                car.getVin()
                                + "," +
                                car.getRegistrationNumber()
                                + "," +
                                owner.getName()
                                + "," +
                                owner.getTel()
                                + "," +
                                owner.getEmail()
                                + "," +
                                owner.getAddress()
                                + "," +
                                record.getCompleted()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}