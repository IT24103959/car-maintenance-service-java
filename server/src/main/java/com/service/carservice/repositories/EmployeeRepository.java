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
    private int nextId = 1;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EmployeeRepository() {
        List<Employee> employee = loadEmployees();
        this.nextId = employee.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<Employee> getAllEmployees() {
        return loadEmployees();
    }

    public void addEmployee(Employee employee) {
        employee.setId(nextId++);
        List<Employee> employees = loadEmployees();
        employees.add(employee);
        persistToFile(employees);
    }

    public void deleteEmployee(int id) {
        List<Employee> employees = loadEmployees();
        employees.removeIf(employee -> employee.getId() == id);
        persistToFile(employees);
    }

    private List<Employee> loadEmployees() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Employee.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }

    private void persistToFile(List<Employee> employees) {
        try {
            objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        return nextId;
    }

}
