package com.service.carservice.repositories;

import com.service.carservice.models.Car;
import com.service.carservice.models.Owner;
import com.service.carservice.models.ServiceRecord;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ServiceRecordRepository {
    private static final String FILE_PATH = "src/main/resources/data/service-history.txt";
    private LinkedList<ServiceRecord> serviceRecords = new LinkedList<>();
    private int nextId = 1;

    public ServiceRecordRepository() {
        loadServiceRecords();
        setNextId();
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecords;
    }

    public void saveServiceRecords(List<ServiceRecord> records) {
        this.serviceRecords = new LinkedList<>(records);
        persistToFile();
    }

    public void addServiceRecord(ServiceRecord record) {
        record.setId(nextId++);
        serviceRecords.add(record);
        persistToFile();
    }

    public void deleteServiceRecord(int id) {
        serviceRecords.removeIf(record -> record.getId() == id);
        persistToFile();
    }

    public ServiceRecord getServiceRecordById(int id) {
        return serviceRecords.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElse(null);
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
                    ServiceRecord record = new ServiceRecord(Integer.parseInt(parts[0]), date, parts[2],
                            Double.parseDouble(parts[3]));
                    record.setCar(car);
                    record.setCompleted(Boolean.parseBoolean(parts[13])); // Assuming the last part is a boolean for
                                                                          // completion status
                    serviceRecords.add(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void persistToFile() {
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
                                record.getCompleted());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNextId() {
        int maxId = serviceRecords.stream()
                .mapToInt(ServiceRecord::getId)
                .max()
                .orElse(0);
        nextId = maxId + 1;
    }
}