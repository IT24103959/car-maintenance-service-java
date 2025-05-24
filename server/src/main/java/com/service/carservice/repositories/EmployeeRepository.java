package com.service.carservice.repositories;

import com.service.carservice.cache.EmployeeList;
import com.service.carservice.models.Employee;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class EmployeeRepository extends BaseRepository {
    private static final String FILE_PATH = "src/main/resources/data/employees.json";
    private final EmployeeList items = new EmployeeList();

    public EmployeeRepository() {
        super();
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(getFilePath()))) {
                Employee[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(getFilePath())),
                        objectMapper.getTypeFactory().constructArrayType(Employee.class));

                for (Employee record : data) {
                    items.insert(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistToFile(EmployeeList items) {
        if (items != null && items.getSize() > 0) {
            try {
                Employee[] data = items.toArray();
                objectMapper.writeValue(Paths.get(getFilePath()).toFile(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNextId() {
        int maxId = 0;
        Employee[] data = items.toArray();
        for (Employee record : data) {
            int id = record.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        nextId = maxId + 1;
    }

    public EmployeeList getAll(){
        return items;
    }

}