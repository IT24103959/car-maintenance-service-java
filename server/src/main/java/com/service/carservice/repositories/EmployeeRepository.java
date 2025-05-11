package com.service.carservice.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import com.service.carservice.models.Employee;

@Repository
public class EmployeeRepository {

    private static final String FILE_PATH = "src/main/resources/data/employees.json";
    private LinkedList<Employee> employees = new LinkedList<>();
    private int nextId = 1;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EmployeeRepository() {
        loadEmployees();
        setNextId();
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        persistToFile();
    }

    public void deleteEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
        persistToFile();
    }

    private void setNextId() {
        int maxId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0);
        nextId = maxId + 1;
    }

    private void loadEmployees() {
        try {
            employees = objectMapper.readValue(Files.readAllBytes(Paths.get(FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Employee.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void persistToFile() {
        try {
            objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), employees);
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist employees to file", e);
        }
    }

    public int getNextId() {
        return nextId;
    }

}
