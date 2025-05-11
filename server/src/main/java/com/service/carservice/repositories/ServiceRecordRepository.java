package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.models.ServiceRecord;

import org.springframework.stereotype.Repository;

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
        int maxId = 0;
        for (ServiceRecord record : records) {
            if (record.getId() > maxId) {
                maxId = record.getId();
            }
        }
        nextId = maxId + 1;
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

    public List<ServiceRecord> loadServiceRecords() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(FILE_PATH));
            return objectMapper.readValue(jsonData,
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, ServiceRecord.class));
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    public void persistToFile(List<ServiceRecord> serviceRecords) {
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