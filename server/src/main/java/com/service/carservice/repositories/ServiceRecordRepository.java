package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.models.ServiceRecord;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ServiceRecordRepository {
    private static final String FILE_PATH = "src/main/resources/data/service-history.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private int nextId;

    public ServiceRecordRepository() {
        List<ServiceRecord> records = loadServiceRecords();
        this.nextId = records.stream()
                .mapToInt(ServiceRecord::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return loadServiceRecords();
    }

    public void saveServiceRecords(List<ServiceRecord> records) {
        persistToFile(records);
    }

    public void addServiceRecord(ServiceRecord newRecord) {
        newRecord.setId(nextId++);
        List<ServiceRecord> records = loadServiceRecords();
        records.add(newRecord);
        persistToFile(records);
    }

    public ServiceRecord getServiceRecordById(int recordId) {
        return loadServiceRecords().stream()
                .filter(record -> record.getId() == recordId)
                .findFirst()
                .orElse(null);
    }

    private List<ServiceRecord> loadServiceRecords() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, ServiceRecord.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    private void persistToFile(List<ServiceRecord> serviceRecords) {
        try {
            objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), serviceRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        return nextId;
    }

}