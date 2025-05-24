package com.service.carservice.repositories;

import com.service.carservice.cache.ServiceRecordList;
import com.service.carservice.models.ServiceRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ServiceRecordRepository extends BaseRepository {
    private static final String FILE_PATH = "src/main/resources/data/service-history.json";
    private final ServiceRecordList items = new ServiceRecordList();

    public ServiceRecordRepository() {
        super();
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(getFilePath()))) {
                ServiceRecord[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(getFilePath())),
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
                objectMapper.writeValue(Paths.get(getFilePath()).toFile(), data);
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
