package com.service.carservice.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.service.carservice.models.Employee;

@Service
public class EmployeeManagerService {

    private static final String FILE_PATH = "src/main/resources/data/employees.txt";
    private LinkedList<Employee> employees;
    private int nextId = 1;

    public EmployeeManagerService() {
        employees = new LinkedList<>();
        loadEmployees();
        setNextId();
    }

    private void setNextId() {
        int maxId = 0;
        for (Employee employee : employees) {
            if (employee.getId() > maxId) {
                maxId = employee.getId();
            }
        }
        nextId = maxId + 1;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(String name, String email) {
        Employee employee = new Employee(nextId, name, email);
        employees.add(employee);
        saveEmployees();
        nextId++;
    }

    public void deleteEmployee(Long id) {
        employees.removeIf(employee -> employee.getId() == id);
        saveEmployees();
        nextId--;
    }

    private void loadEmployees() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                Employee employee = new Employee(id, name, email);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployees() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee employee : employees) {
                bw.write(employee.getId() + "," + employee.getName() + "," + employee.getEmail());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
