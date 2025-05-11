package com.service.carservice.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.models.Employee;

@Repository
public class EmployeeRepository {

    private static final String JSON_FILE_PATH = "src/main/resources/data/employees.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private int nextId;

    public EmployeeRepository() {
        List<Employee> employees = loadEmployees(); // Directly load employees from JSON
        this.nextId = employees.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<Employee> getAllEmployees() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(JSON_FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Employee.class));
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    private List<Employee> loadEmployees() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(JSON_FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Employee.class));
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void addEmployee(Employee employee) {
        employee.setId(nextId++); // Use nextId and increment
        List<Employee> employees = loadEmployees();
        employees.add(employee);
        persistToFile(employees);
    }

    public void deleteEmployee(int id) {
        List<Employee> employees = getAllEmployees();
        employees.removeIf(employee -> employee.getId() == id);
        persistToFile(employees);
    }

    public void persistToFile(List<Employee> employees) {
        try {
            objectMapper.writeValue(Paths.get(JSON_FILE_PATH).toFile(), employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        return nextId;
    }

}
