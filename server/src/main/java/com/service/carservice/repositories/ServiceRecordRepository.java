package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.cache.ServiceRecordList;
import com.service.carservice.models.ServiceRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ServiceRecordRepository {
    private static final String FILE_PATH = "src/main/resources/data/service-history.json";
    private final ServiceRecordList items = new ServiceRecordList();
    private int nextId;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public ServiceRecordRepository() {
        loadFromFile();
        setNextId();
    }

    public int getNextId(boolean increment){
        int id = nextId;
        if(increment){
            nextId++;
        }
        return id;
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                ServiceRecord[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(FILE_PATH)),
                        objectMapper.getTypeFactory().constructArrayType(ServiceRecord.class));

                for (ServiceRecord record : data) {
                    items.insert(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistToFile(ServiceRecordList items) {
        if (items != null && items.getSize() > 0) {
            try {
                ServiceRecord[] data = items.toArray();
                objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNextId() {
        int maxId = 0;
        ServiceRecord[] data = items.toArray();
        for (ServiceRecord record : data) {
            int id = record.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        nextId = maxId + 1;
    }

    public ServiceRecordList getAll(){
        return items;
    }
}
