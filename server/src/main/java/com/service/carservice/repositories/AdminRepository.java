package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.cache.AdminList;
import com.service.carservice.models.Admin;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class AdminRepository {
    private static final String FILE_PATH = "src/main/resources/data/admins.json";
    private final AdminList items = new AdminList();
    private int nextId;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public int getNextId(boolean increment){
        int id = nextId;
        if(increment){
            nextId++;
        }
        return id;
    }

    public AdminRepository() {
        loadFromFile();
        setNextId();
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                Admin[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(FILE_PATH)),
                        objectMapper.getTypeFactory().constructArrayType(Admin.class));

                for (Admin record : data) {
                    items.insert(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistToFile(AdminList items) {
        if (items != null && items.getSize() > 0) {
            try {
                Admin[] data = items.toArray();
                objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNextId() {
        int maxId = 0;
        Admin[] data = items.toArray();
        for (Admin record : data) {
            int id = record.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        nextId = maxId + 1;
    }

    public AdminList getAll(){
        return items;
    }

}