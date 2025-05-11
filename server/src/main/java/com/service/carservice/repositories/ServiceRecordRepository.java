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

    public ServiceRecordRepository() {
        // Constructor is now empty as LinkedList management is moved to
        // ServiceRecordService
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return loadServiceRecords();
    }

    public void saveServiceRecords(List<ServiceRecord> records) {
        persistToFile(records);
    }

    public void addServiceRecord(ServiceRecord record) {
        throw new UnsupportedOperationException("Operation moved to ServiceRecordService");
    }

    public void deleteServiceRecord(int id) {
        throw new UnsupportedOperationException("Operation moved to ServiceRecordService");
    }

    public ServiceRecord getServiceRecordById(int id) {
        return loadServiceRecords().stream()
                .filter(record -> record.getId() == id)
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
}