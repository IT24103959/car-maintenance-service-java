package com.service.carservice.services;

import com.service.carservice.models.Employee;
import com.service.carservice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeManagerService {

    private final EmployeeRepository employeeRepository;
    private LinkedList<Employee> employees;

    @Autowired
    public EmployeeManagerService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.employees = new LinkedList<>(employeeRepository.getAllEmployees());
    }

    public void addEmployee(Employee employee) {
        employee.setId(employeeRepository.getNextId()); // Fetch nextId from repository
        employees.add(employee);
        employeeRepository.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void deleteEmployee(int id) {
        employees.removeIf(employee -> employee.getId() == id);
    }
}
